/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.annotation.Column;
import com.dostojic.climbers.dbbr.improved.annotation.CompositeId;
import com.dostojic.climbers.dbbr.improved.annotation.Table;

/**
 *
 * @author Dejan.Ostojic
 */
@Table(name = "route", autoIncrement = false)
public class RouteDto {
    
    
    @CompositeId
    private RouteCompositeId id;

    @Column(name = "name", isPrimaryKey = false)
    private String name;
    
    @Column(name = "grade", isPrimaryKey = false)
    private String grade;

    public RouteDto() {
    }

    public RouteDto(RouteCompositeId id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }


    public RouteCompositeId getId() {
        return id;
    }

    public void setId(RouteCompositeId id) {
        this.id = id;
    }
    
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RouteDto{" + "id=" + id + ", name=" + name + ", grade=" + grade + '}';
    }

   
}
