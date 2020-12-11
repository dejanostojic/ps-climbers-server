/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.main;

import com.dostojic.climbers.controller.Controller;
import com.dostojic.climbers.repositoryinmemoryadapter.ClimberRepositoryInMemoryImpl;
import com.dostojic.climbers.repositoryinmemoryadapter.CompetitionRepositoryInMemoryImpl;
import com.dostojic.climbers.repositoryinmemoryadapter.UserRepositoryInMemoryImpl;

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
