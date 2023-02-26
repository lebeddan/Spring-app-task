package com.example.task.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateTechnology> technologies = new ArrayList<CandidateTechnology>();

    public Candidate(){}
    public Candidate(String name, String email) {
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CandidateTechnology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<CandidateTechnology> technologies) {
        this.technologies = technologies;
    }

    public void addTechnology(CandidateTechnology technology) {
        this.technologies.add(technology);
        technology.setCandidate(this);
    }

    public void removeTechnology(CandidateTechnology technology) {
        this.technologies.remove(technology);
        technology.setCandidate(null);
    }
}
