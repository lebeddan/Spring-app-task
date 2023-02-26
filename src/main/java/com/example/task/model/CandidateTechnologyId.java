package com.example.task.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CandidateTechnologyId implements Serializable {
    private Long candidateId;
    private Long technologyId;

    public CandidateTechnologyId() {
    }

    public CandidateTechnologyId(Long candidateId, Long technologyId) {
        this.candidateId = candidateId;
        this.technologyId = technologyId;
    }

    // getters and setters

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
        this.technologyId = technologyId;
    }
}
