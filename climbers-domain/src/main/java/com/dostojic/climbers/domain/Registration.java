/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author planina
 */
public class Registration implements Serializable {

    Competition competition;
    Climber climber;
    Integer startNumber;
    Integer totalOrd;
    Boolean paid;
    Date createdDate;
    Date paidDate;
    RegistrationFee registrationFee;
    List<RouteClimbed> climbedRoutes;

    public Registration() {
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Climber getClimber() {
        return climber;
    }

    public void setClimber(Climber climber) {
        this.climber = climber;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getTotalOrd() {
        return totalOrd;
    }

    public void setTotalOrd(Integer totalOrd) {
        this.totalOrd = totalOrd;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public RegistrationFee getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(RegistrationFee registrationFee) {
        this.registrationFee = registrationFee;
    }

    public List<RouteClimbed> getClimbedRoutes() {
        return climbedRoutes;
    }

    public void setClimbedRoutes(List<RouteClimbed> climbedRoutes) {
        this.climbedRoutes = climbedRoutes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.competition);
        hash = 71 * hash + Objects.hashCode(this.climber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registration other = (Registration) obj;
        if (!Objects.equals(this.competition, other.competition)) {
            return false;
        }
        if (!Objects.equals(this.climber, other.climber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Registration{" + "competition=" + competition + ", climber=" + climber + ", startNumber=" + startNumber + ", totalOrd=" + totalOrd + ", paid=" + paid + ", createdDate=" + createdDate + ", paidDate=" + paidDate + ", registrationFee=" + registrationFee + ", climbedRoutes=" + climbedRoutes + '}';
    }

}
