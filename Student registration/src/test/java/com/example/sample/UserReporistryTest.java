package com.example.sample;

import com.example.sample.Entity.User;
import com.example.sample.Entity.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class UserReporistryTest {
    @Autowired
    private UserRepo userRepo;

    @Test
    public void testAddNew(){
        User user=new User();
        user.setEmail("doe@gmail.com");
        user.setPassword("12345678");
        user.setName("doe");
        user.setAddress("Panadura");
        user.setPhone("0776545676");

        User saved=userRepo.save(user);
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll(){
        Iterable<User> users=userRepo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user:users){
            System.out.println(user);
        }

    }

    @Test
    public void testUpdate(){
        int userId=1;
        Optional<User> optionalUser=userRepo.findById(userId);
        User user=optionalUser.get();
        user.setPassword("zzzzzzzzzzzzz");
        userRepo.save(user);
        User updateUser=userRepo.findById(userId).get();
        Assertions.assertThat(updateUser.getPassword()) .isEqualTo("zzzzzzzzzzzzz");

    }
    @Test
    public void testGet(){
        int userId=2;
        Optional<User>optionalUser=userRepo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelwte(){
        int userId=2;
        userRepo.deleteById(userId);
        Optional<User>optionalUser=userRepo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
