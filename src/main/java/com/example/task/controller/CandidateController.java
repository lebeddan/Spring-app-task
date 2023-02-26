package com.example.task.controller;

import com.example.task.model.Candidate;
import com.example.task.model.CandidateTechnology;
import com.example.task.model.Technology;
import com.example.task.repository.CandidateRepository;
import com.example.task.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidateRequest request) {
        Candidate candidate = new Candidate(request.getName(), request.getEmail());
        candidateRepository.save(candidate);
        return ResponseEntity.ok(candidate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody CandidateRequest request) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
        if (!optionalCandidate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Candidate existingCandidate = optionalCandidate.get();
        existingCandidate.setName(request.getName());
        existingCandidate.setEmail(request.getEmail());
        existingCandidate.setTechnologies(request.getTechnologies());

        candidateRepository.save(existingCandidate);

        return ResponseEntity.ok(existingCandidate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long id) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
        if (!optionalCandidate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Candidate candidate = optionalCandidate.get();
        candidateRepository.delete(candidate);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
        if (!optionalCandidate.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Candidate candidate = optionalCandidate.get();
        return ResponseEntity.ok(candidate);
    }

    public static class CandidateRequest {
        private Long candidateId;
        private String name;
        private String email;

        private List<CandidateTechnology> technologies;

        public List<CandidateTechnology> getTechnologies() {
            return technologies;
        }

        public void setTechnologies(List<CandidateTechnology> technologies) {
            this.technologies = technologies;
        }

        public Long getCandidateId() {
            return candidateId;
        }

        public void setCandidateId(Long candidateId) {
            this.candidateId = candidateId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
