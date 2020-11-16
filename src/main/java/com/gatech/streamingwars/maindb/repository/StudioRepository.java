package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio,Long> {
}
