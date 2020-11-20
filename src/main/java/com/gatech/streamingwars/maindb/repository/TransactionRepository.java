package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT d FROM Transaction d WHERE d.demographicName = :demographicName and d.currentMonthYear = :currentMonthYear")
    List<Transaction> findByNameAndCurrentMonthYear(@Param("demographicName")  String demographicName, @Param("currentMonthYear")  String currentMonthYear);

}
