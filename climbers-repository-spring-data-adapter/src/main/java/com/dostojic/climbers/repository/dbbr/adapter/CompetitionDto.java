/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.annotation.Column;
import com.dostojic.climbers.dbbr.improved.annotation.Table;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dejan.Ostojic
 */
@Table(name = "competition", autoIncrement = true)
public class CompetitionDto {

    @Column(name = "id", isPrimaryKey = true)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "registration_open")
    private java.sql.Timestamp registrationOpen;
    
    @Column(name = "registration_close")
    private java.sql.Timestamp registrationClose;
    
    @Column(name = "event_start_date")
    private java.sql.Timestamp eventStart;
    
    private List<RouteDto> routes;
    private List<RegistrationFeeDto> registrationFees;

    public CompetitionDto() {
    }

    public CompetitionDto(Integer id, String name, String description, java.sql.Timestamp registrationOpen, java.sql.Timestamp registrationClose, java.sql.Timestamp eventStart, List<RouteDto> routes, List<RegistrationFeeDto> registrationFees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.registrationOpen = registrationOpen;
        this.registrationClose = registrationClose;
        this.eventStart = eventStart;
        this.routes = routes;
        this.registrationFees = registrationFees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public java.sql.Timestamp getEventStart() {
        return eventStart;
    }

    public void setEventStart(java.sql.Timestamp eventStart) {
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

    public java.sql.Timestamp getRegistrationOpen() {
        return registrationOpen;
    }

    public void setRegistrationOpen(java.sql.Timestamp registrationOpen) {
        this.registrationOpen = registrationOpen;
    }

    public java.sql.Timestamp getRegistrationClose() {
        return registrationClose;
    }

    public void setRegistrationClose(java.sql.Timestamp registrationClose) {
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
