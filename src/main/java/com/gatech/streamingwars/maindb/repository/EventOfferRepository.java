package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventOfferRepository extends JpaRepository<EventOffer,Long> {
   List<EventOffer> findByService(StreamingService service_id);

   @Query("SELECT d FROM EventOffer d WHERE d.event=:event_id and d.service= :service_id and d.currentMonthYear =:currentMonthYear")
   List<EventOffer> findByServiceEventCurrentYear(@Param("event_id") Event event, @Param("service_id") StreamingService service_id, @Param("currentMonthYear") String currentMonthYear);

}
