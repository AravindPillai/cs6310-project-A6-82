package com.gatech.streamingwars.repository;

import com.gatech.streamingwars.model.main.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
