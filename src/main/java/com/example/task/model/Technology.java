package com.example.task.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "technologies")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "technology", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateTechnology> candidates = new ArrayList<CandidateTechnology>();

    public Technology(){}
    public Technology(String name, Integer level, String note) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CandidateTechnology> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateTechnology> candidates) {
        this.candidates = candidates;
    }

    public void addCandidate(CandidateTechnology candidate) {
        this.candidates.add(candidate);
        candidate.setTechnology(this);
    }

    public void removeCandidate(CandidateTechnology candidate) {
        this.candidates.remove(candidate);
        candidate.setTechnology(null);
    }
}
