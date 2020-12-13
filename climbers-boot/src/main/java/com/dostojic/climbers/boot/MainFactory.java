/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.boot;

import com.dostojic.climbers.logic.Controller;
import com.dostojic.climbers.repository.inmemory.adapter.ClimberRepositoryInMemoryImpl;
import com.dostojic.climbers.repository.inmemory.adapter.CompetitionRepositoryInMemoryImpl;
import com.dostojic.climbers.repository.inmemory.adapter.UserRepositoryInMemoryImpl;

/**
 *
 * @author planina
 */
public class MainFactory {
    
    public static Controller getController(){
        return new Controller(new ClimberRepositoryInMemoryImpl(),
                new CompetitionRepositoryInMemoryImpl(), 
                new UserRepositoryInMemoryImpl());
    }
}
