/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.BrokerBazePodataka;
import com.dostojic.climbers.dbbr.BrokerBazePodataka1;
import com.dostojic.climbers.dbbr.GeneralDObject;
import com.dostojic.climbers.dbbr.UserGeneral;
import com.dostojic.climbers.domain.User;
import com.dostojic.climbers.logic.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author planina
 */
public class UserRepositoryDbbrImpl implements UserRepository{
    BrokerBazePodataka broker = new BrokerBazePodataka1("db");

    @Override
    public List<User> getAll() {
        return broker.findRecord(new UserGeneral(), null)
                .stream()
                .map(gen -> fromGeneral((UserGeneral)gen))
                .collect(Collectors.toList());
                
    }
    
    
    public User fromGeneral(UserGeneral gUser){
        throw new RuntimeException("IMPLEMENT THIS");
    }
    
    
    
}
