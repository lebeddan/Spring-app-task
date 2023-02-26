package com.example.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "candidateTechnology")
public class CandidateTechnology {
    @EmbeddedId
    private CandidateTechnologyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "candi_id")
    @JsonIgnore
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "tech_id")
    @JsonIgnore
    private Technology technology;

    private int skillLevel;
    private String note;

    public CandidateTechnology() {
    }

    public CandidateTechnology(Candidate candidate, Technology technology, int skillLevel, String note) {
        this.candidate = candidate;
        this.technology = technology;
        this.skillLevel = skillLevel;
        this.note = note;
        this.id = new CandidateTechnologyId(candidate.getId(), technology.getId());
    }

    // getters and setters

    public CandidateTechnologyId getId() {
        return id;
    }

    public void setId(CandidateTechnologyId id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
