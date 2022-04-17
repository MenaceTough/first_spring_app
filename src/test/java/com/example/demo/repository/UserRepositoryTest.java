package com.example.demo.repository;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.dto.UserDtoTest.userTest;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByLogin() {
        userRepository.save(userTest());
        User foundUser = userRepository.findByLogin(userTest().getLogin());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo(userTest().getName());
    }
}