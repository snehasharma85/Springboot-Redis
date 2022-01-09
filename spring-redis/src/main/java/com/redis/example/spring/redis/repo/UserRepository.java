package com.redis.example.spring.redis.repo;

import com.redis.example.spring.redis.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findAll();

}
