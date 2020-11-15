package com.gatech.streamingwars.repository;

import com.gatech.streamingwars.model.main.Event;
import com.gatech.streamingwars.model.main.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio,Long> {
}
