package com.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ServiceUriBuilder {

    private LoadBalancerClient loadBalancerClient;

    public ServiceUriBuilder(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    public String getServiceUri(String serviceId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        Assert.notNull(serviceInstance, "Instance id " + serviceId + " is not available");
        return serviceInstance.getUri().toString();
    }

}
