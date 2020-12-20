/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.annotation.Table;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dejan.Ostojic
 */
@Table(name = "competition", autoIncrement = false)
public class CompetitionDto {

    private Integer id;
    private String description;
    private Date registrationOpen;
    private Date registrationClose;
    private Date eventStart;
    private List<RouteDto> routes;
    private List<RegistrationFeeDto> registrationFees;

    public CompetitionDto() {
    }

    public CompetitionDto(Integer id, String description, Date registrationOpen, Date registrationClose, Date eventStart) {
        this.id = id;
        this.description = description;
        this.registrationOpen = registrationOpen;
        this.registrationClose = registrationClose;
        this.eventStart = eventStart;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegistrationOpen() {
        return registrationOpen;
    }

    public void setRegistrationOpen(Date registrationOpen) {
        this.registrationOpen = registrationOpen;
    }

    public Date getRegistrationClose() {
        return registrationClose;
    }

    public void setRegistrationClose(Date registrationClose) {
        this.registrationClose = registrationClose;
    }

    public List<RouteDto> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDto> routes) {
        this.routes = routes;
    }

    public List<RegistrationFeeDto> getRegistrationFees() {
        return registrationFees;
    }

    public void setRegistrationFees(List<RegistrationFeeDto> registrationFees) {
        this.registrationFees = registrationFees;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final CompetitionDto other = (CompetitionDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Competition{" + "id=" + id + ", description=" + description + ", registrationOpen=" + registrationOpen + ", registrationClose=" + registrationClose + ", eventStart=" + eventStart + '}';
    }
}
