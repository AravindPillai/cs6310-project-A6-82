package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
