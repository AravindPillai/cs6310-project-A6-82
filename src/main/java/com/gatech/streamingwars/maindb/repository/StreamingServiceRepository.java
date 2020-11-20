package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.StreamingService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingServiceRepository extends JpaRepository<StreamingService,Long> {
   StreamingService findByShortName(String shortName);
}
