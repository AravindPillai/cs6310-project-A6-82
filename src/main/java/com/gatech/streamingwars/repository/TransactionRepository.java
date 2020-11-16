package com.gatech.streamingwars.repository;

import com.gatech.streamingwars.model.main.Studio;
import com.gatech.streamingwars.model.main.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
