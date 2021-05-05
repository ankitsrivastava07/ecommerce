package ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ecommerce.user.users.dao.entity.UserEntity;
import ecommerce.user.users.dao.repository.UserRepository;

@SpringBootTest
class EcommerceApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void getAllUsers() {

		List<String> list = userRepository.findAll().stream().map(UserEntity::getFirstName)
				.collect(Collectors.toList());

		assertEquals(1, list.size());
	}

	@Test
	public void getOrdersFromUserId() {

		List<String> list = userRepository.findAll().stream().map(UserEntity::getFirstName)
				.collect(Collectors.toList());

		assertEquals(1, list.size());
	}

}
