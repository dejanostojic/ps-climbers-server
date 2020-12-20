/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.annotation.Column;
import com.dostojic.climbers.dbbr.improved.annotation.Table;

/**
 *
 * @author Dejan.Ostojic
 */
@Table(name = "climber", autoIncrement = true)
public class ClimberDto {
    
    @Column(name = "id", isPrimaryKey = true)
    private Integer id;
        
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    
    
}
