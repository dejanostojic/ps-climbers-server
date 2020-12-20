/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.boot;

import com.dostojic.climbers.logic.Controller;
import com.dostojic.climbers.repository.dbbr.adapter.ClimberRepositoryDbbrImpl;
import com.dostojic.climbers.repository.dbbr.adapter.CompetitionRepositoryDbbrImpl;
import com.dostojic.climbers.repository.dbbr.adapter.TransactionManagerImpl;
import com.dostojic.climbers.repository.dbbr.adapter.UserRepositoryDbbrImpl;

/**
 *
 * @author planina
 */
public class MainFactory {

    public static Controller getController() {
        /*
        return new Controller(new ClimberRepositoryInMemoryImpl(),
                new CompetitionRepositoryInMemoryImpl(), 
                new UserRepositoryInMemoryImpl());
         */
        return new Controller(new TransactionManagerImpl(),
                new ClimberRepositoryDbbrImpl(),
                new CompetitionRepositoryDbbrImpl(),
                new UserRepositoryDbbrImpl());
    }
}
