package com.university.lsd.repository;

import com.university.lsd.model.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, UUID> {
    Optional<Museum> findByName(final String name);
}
