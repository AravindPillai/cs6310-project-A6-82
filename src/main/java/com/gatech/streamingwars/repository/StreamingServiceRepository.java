package com.gatech.streamingwars.repository;

import com.gatech.streamingwars.model.main.StreamingService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingServiceRepository extends JpaRepository<StreamingService,Long> {
}
