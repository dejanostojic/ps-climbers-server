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
public class RouteCompositeId {

    @Column(name = "competition_id", isPrimaryKey = true)
    private Integer competitionId;

    @Column(name = "ord", isPrimaryKey = true)
    private Integer ord;

    public RouteCompositeId() {
    }

    public RouteCompositeId(Integer competitionId, Integer ord) {
        this.competitionId = competitionId;
        this.ord = ord;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    @Override
    public String toString() {
        return "RouteCompositeId{" + "competitionId=" + competitionId + ", ord=" + ord + '}';
    }

}
