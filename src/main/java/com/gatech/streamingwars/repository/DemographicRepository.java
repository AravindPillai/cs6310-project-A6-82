package com.gatech.streamingwars.repository;

import com.gatech.streamingwars.model.main.Account;
import com.gatech.streamingwars.model.main.DemographicGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemographicRepository extends JpaRepository<DemographicGroup,Long> {
}
