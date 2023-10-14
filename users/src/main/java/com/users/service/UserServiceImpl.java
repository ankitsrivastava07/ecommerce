package com.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.convertor.ConvertDtoToEntity;
import com.users.dao.UserDao;
import com.users.dao.entity.UserEntity;
import com.users.dto.ApiResponse;
import com.users.dto.CreateUserDto;
import com.users.dto.LoginRequestDto;
import com.utility.constant.UserServiceConstant;
import com.utility.converter.DtoToMapConversion;
import com.utility.dto.CreateOrderDto;
import com.utility.restApi.RestTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.*;

import static com.users.constant.UserConstant.*;

@Service
public class UserServiceImpl implements UserService {
    UserDao userDao;

    RestTemplateHelper restTemplateHelper;

    ServiceUriBuilder serviceUriBuilder;

    private LoadBalancerClient loadBalancerClient;

    @Autowired
    public UserServiceImpl(LoadBalancerClient loadBalancerClient, RestTemplateHelper restTemplateHelper, UserDao userDao,
                           ServiceUriBuilder serviceUriBuilder) {
        this.loadBalancerClient = loadBalancerClient;
        this.restTemplateHelper = restTemplateHelper;
        this.userDao = userDao;
        this.serviceUriBuilder = serviceUriBuilder;
    }

    @Override
    public ApiResponse login(LoginRequestDto loginRequestDto) {
        UserEntity user = userDao.findByMobileOrEmail(loginRequestDto.getEmailOrMobile());

        if (user == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("accountErr", "Account does not exist");
            return ApiResponse.builder().setMsg("Account does not exist").setStatus(Boolean.FALSE).setError(error).build();
        }

        if (new BCryptPasswordEncoder().matches(loginRequestDto.getPassword(), user.getPassword())) {
            return ApiResponse.builder().setStatus(TRUE).setMsg(LOGIN_SUCCESS_DEFAULT_MSG).build();
        } else {
            return ApiResponse.builder().setStatus(FALSE).setMsg(INVALID_CREDENTIAL_DEFAULT_MSG).build();
        }
    }

    @Override
    public ApiResponse createUser(CreateUserDto createUserDto) {

        createUserDto.setPassword(new BCryptPasswordEncoder().encode(createUserDto.getPassword()));

        UserEntity user = ConvertDtoToEntity.dtoToEntityConvertor(createUserDto, UserEntity.class);

        Object value;
        if ((value = userDao.findByEmail(createUserDto.getEmail())) != null) {
            return ApiResponse.builder().setStatus(FALSE).setMsg(ACCOUNT_ALREADY_EXIST_DEFAULT_MSG).build();
        }

        user = userDao.save(user);
        return ApiResponse.builder().setMsg(ACCOUNT_CREATED_SUCCESSFULLY_DEFAULT_MSG).setStatus(TRUE).build();
    }

    @Override
    public ApiResponse getAllOrdersByUserId(Long userId) throws ServiceUnavailableException {

        ServiceInstance serviceInstance = loadBalancerClient.choose(UserServiceConstant.ORDER_SERVICE_INSTANCE_ID);

        Assert.notNull(serviceInstance, "Order Service Instance is not available");
        //throw new ServiceUnavailableException("orderService is unavailable");

        StringBuilder orderURL = new StringBuilder(URI.create(String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/api/v1/order/" + userId)).toString());

        Map<String, Object> response = restTemplateHelper.get(orderURL, new HashMap<>());

        return ApiResponse.builder().setData(response).setStatus(Boolean.TRUE).setMsg("Success").build();
    }

    @Override
    public ApiResponse createOrder(String userId, CreateOrderDto createOrderDto) {
        StringBuilder url = new StringBuilder(serviceUriBuilder.getServiceUri(UserServiceConstant.ORDER_SERVICE_INSTANCE_ID))
                .append("/api/v1/order/create");

        Map<String, Object> destinationType = DtoToMapConversion.convertDtoToMap(createOrderDto, Map.class);
        Map<String, Object> response = restTemplateHelper.post(url, destinationType, new HashMap<>());

        return ApiResponse
                .builder()
                .setMsg("Success")
                .setStatus(Boolean.TRUE)
                .setData(response)
                .build();

    }
}
