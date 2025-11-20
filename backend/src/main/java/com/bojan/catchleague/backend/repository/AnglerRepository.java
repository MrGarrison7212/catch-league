package com.bojan.catchleague.backend.repository;

import com.bojan.catchleague.backend.model.Angler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnglerRepository extends JpaRepository<Angler, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
