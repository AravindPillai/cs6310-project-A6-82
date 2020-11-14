package com.gatech.streamingwars.repository;

import com.gatech.streamingwars.model.main.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByName(String name);
}
