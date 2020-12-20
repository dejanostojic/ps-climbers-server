/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.*;
import com.dostojic.climbers.dbbr.improved.annotation.Column;
import com.dostojic.climbers.dbbr.improved.annotation.Table;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author planina
 */
@Table(name = "user", autoIncrement = true)
public class UserDto {
    
    @Column(name = "id", isPrimaryKey = true)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
