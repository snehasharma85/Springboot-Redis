package com.redis.example.spring.redis.service;

import com.redis.example.spring.redis.entity.UserEntity;
import com.redis.example.spring.redis.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final String KEY = "USER_KEY";

    @Autowired
    private RedisTemplate redisTemplate;

    private HashOperations hashOperations;

    @CachePut(value = KEY, key = "#user.id")
    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Cacheable(value = KEY, key = "#id")
    public Optional<UserEntity> getUser(Long id) {
        return userRepository.findById(id);
    }

    @CacheEvict(value = KEY, allEntries = false, key = "#id", condition = "#result == true")
    public Optional<UserEntity> deleteUser(Long id) {
        userRepository.deleteById(id);
        return userRepository.findById(id);
    }
}
