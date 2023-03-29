package com.restaurantreview.api.repository;

import com.restaurantreview.api.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String name);
    Boolean existsByUsername(String username);
}
