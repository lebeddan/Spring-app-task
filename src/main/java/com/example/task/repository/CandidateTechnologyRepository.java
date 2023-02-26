package com.example.task.repository;

import com.example.task.model.CandidateTechnology;
import com.example.task.model.CandidateTechnologyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateTechnologyRepository extends JpaRepository<CandidateTechnology, Long> {
    Optional<CandidateTechnology> findById(CandidateTechnologyId candidateTechnologyId);

}
