/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.annotation.Column;

/**
 *
 * @author Dejan.Ostojic
 */
public class RegistrationCompositeId {
    @Column(name = "competition_id", isPrimaryKey = true)
    private Integer competitionId;

    @Column(name = "start_number", isPrimaryKey = true)
    private Integer startNumber;

    public RegistrationCompositeId() {
    }

    public RegistrationCompositeId(Integer competitionId, Integer startNumber) {
        this.competitionId = competitionId;
        this.startNumber = startNumber;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }
    
    
}
