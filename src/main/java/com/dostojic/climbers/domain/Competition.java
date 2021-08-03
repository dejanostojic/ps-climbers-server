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
public class Competition implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Date registrationOpen;
    private Date registrationClose;
    private Date eventStart;
    private List<Route> routes;
    private List<RegistrationFee> registrationFees;
    private List<Registration> registrations;

    public Competition() {
    }

    public Competition(Integer id, String name, String description, Date registrationOpen,Date registrationClose,
            Date eventStart, List<Route> routes, List<RegistrationFee> registrationFees, List<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.registrationOpen = registrationOpen;
        this.registrationClose = registrationClose;
        this.eventStart = eventStart;
        this.routes = routes;
        this.registrationFees = registrationFees;
        this.registrations = registrations;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<RegistrationFee> getRegistrationFees() {
        return registrationFees;
    }

    public void setRegistrationFees(List<RegistrationFee> registrationFees) {
        this.registrationFees = registrationFees;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
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
        final Competition other = (Competition) obj;
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
