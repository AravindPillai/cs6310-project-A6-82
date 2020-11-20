package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.Account;
import com.gatech.streamingwars.maindb.model.EventOffer;
import com.gatech.streamingwars.maindb.model.StreamingService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventOfferRepository extends JpaRepository<EventOffer,Long> {
   List<EventOffer> findByService(StreamingService service_id);
}
