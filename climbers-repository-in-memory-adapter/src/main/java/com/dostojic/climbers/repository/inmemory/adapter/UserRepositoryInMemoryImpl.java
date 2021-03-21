/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.inmemory.adapter;

import com.dostojic.climbers.domain.User;
import com.dostojic.climbers.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class UserRepositoryInMemoryImpl implements UserRepository{
    
    private final List<User> users;

    public UserRepositoryInMemoryImpl() {
        users = new ArrayList<>();
        users.add(new User(1l, "Admin", "Admin", "admin", "admin"));
    }
     
    
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
