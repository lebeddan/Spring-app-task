package com.example.task.controller;

import com.example.task.model.Candidate;
import com.example.task.model.Technology;
import com.example.task.repository.CandidateRepository;
import com.example.task.repository.TechnologyRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {
    @Autowired
    private TechnologyRepository technologyRepository;

    @PostMapping
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology technology) {
        technologyRepository.save(technology);
        return ResponseEntity.ok(technology);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable Long id, @RequestBody Technology technology) {
        Optional<Technology> optionalTechnology = technologyRepository.findById(id);
        if (!optionalTechnology.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Technology existingTechnology = optionalTechnology.get();
        existingTechnology.setName(technology.getName());
        existingTechnology.setCandidates(technology.getCandidates());
        technologyRepository.save(existingTechnology);

        return ResponseEntity.ok(existingTechnology);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTechnology(@PathVariable Long technologyId) {
        Optional<Technology> optionalTechnology = technologyRepository.findById(technologyId);
        if (!optionalTechnology.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Technology technology = optionalTechnology.get();
        technologyRepository.delete(technology);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Technology>> getAllTechnologies() {
        List<Technology> technologies = technologyRepository.findAll();
        return ResponseEntity.ok(technologies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Long id) {
        Optional<Technology> optionalTechnology = technologyRepository.findById(id);
        if (!optionalTechnology.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Technology candidate = optionalTechnology.get();
        return ResponseEntity.ok(candidate);
    }
}

