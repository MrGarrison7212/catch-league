package com.bojan.catchleague.backend.repository;

import com.bojan.catchleague.backend.model.FishingLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingLocationRepository extends JpaRepository<FishingLocation, Long> {
}
