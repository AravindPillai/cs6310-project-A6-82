package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
