package com.gatech.streamingwars.repository;

import com.gatech.streamingwars.model.main.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
