package com.example.sample.Entity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
        public Long countById(int id);
}
