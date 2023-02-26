package com.example.task.controller;

import com.example.task.model.Candidate;
import com.example.task.model.CandidateTechnology;
import com.example.task.model.CandidateTechnologyId;
import com.example.task.model.Technology;
import com.example.task.repository.CandidateRepository;
import com.example.task.repository.CandidateTechnologyRepository;
import com.example.task.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidates/{candidateId}/technologies")
public class CandidateTechnologyController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private CandidateTechnologyRepository candidateTechnologyRepository;

    @GetMapping
    public  ResponseEntity<List<CandidateTechnology>> getCandidateTechnologies(@PathVariable Long candidateId) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(candidateId);
        if (!optionalCandidate.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Candidate existingCandidate = optionalCandidate.get();

        return ResponseEntity.ok(existingCandidate.getTechnologies());
    }

    @PostMapping
    public ResponseEntity<CandidateTechnology> addTechnologyToCandidate(@PathVariable Long candidateId, @RequestBody CandidateTechnologyRequest request) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(candidateId);
        if (!optionalCandidate.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Technology> optionalTechnology = technologyRepository.findById(request.getTechnologyId());
        if (!optionalTechnology.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Candidate existingCandidate = optionalCandidate.get();
        Technology existingTechnology = optionalTechnology.get();

        CandidateTechnology candidateTechnology = new CandidateTechnology(existingCandidate, existingTechnology, request.getSkillLevel(), request.getNote());
        candidateTechnologyRepository.save(candidateTechnology);
        return ResponseEntity.ok(candidateTechnology);
    }

    // update a technology for a candidate
    @PutMapping("/{technologyId}")
    public ResponseEntity<CandidateTechnology> updateCandidateTechnology(@PathVariable Long candidateId,
                                                         @PathVariable Long technologyId,
                                                         @RequestBody CandidateTechnologyRequest request) {
        Optional<CandidateTechnology> candidateTechnology = candidateTechnologyRepository.findById(new CandidateTechnologyId(candidateId, technologyId));
        if (!candidateTechnology.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        CandidateTechnology existingCandidateTechnology = candidateTechnology.get();

        existingCandidateTechnology.setSkillLevel(request.getSkillLevel());
        existingCandidateTechnology.setNote(request.getNote());

        candidateTechnologyRepository.save(existingCandidateTechnology);
        return ResponseEntity.ok(existingCandidateTechnology);
    }

    public static class CandidateTechnologyRequest {
        private Long technologyId;
        private int skillLevel;
        private String note;

        public Long getTechnologyId() {
            return technologyId;
        }

        public void setTechnologyId(Long technologyId) {
            this.technologyId = technologyId;
        }

        public int getSkillLevel() {
            return skillLevel;
        }

        public void setSkillLevel(int skillLevel) {
            if (skillLevel >= 1 && skillLevel <= 10)
                this.skillLevel = skillLevel;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}

