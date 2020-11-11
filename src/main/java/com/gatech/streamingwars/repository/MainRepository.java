package com.gatech.streamingwars.repository;

import com.gatech.streamingwars.model.main.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<Account,Long> {
}
