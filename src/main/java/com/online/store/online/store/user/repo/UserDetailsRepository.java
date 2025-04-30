package com.online.store.online.store.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.store.online.store.user.entity.UserDetailsEntity;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {
    
    Optional<UserDetailsEntity> findByUsernameIgnoringCase(String username);
    
    boolean existsByUsername(String username);
}

