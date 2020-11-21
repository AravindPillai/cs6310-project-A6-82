package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event,Long> {
    public Event findEventByName(String eventName);
    @Query("SELECT d FROM Event d WHERE d.name=:eventName and d.year=:eventYear")
    public Event findByEventNameAndYear(@Param("eventName")String eventName, @Param("eventYear")int eventYear);

}
