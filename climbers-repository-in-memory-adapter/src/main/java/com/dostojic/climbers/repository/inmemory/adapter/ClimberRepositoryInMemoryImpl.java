/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.inmemory.adapter;

import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.logic.ClimberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author planina
 */
public class ClimberRepositoryInMemoryImpl implements ClimberRepository {

    private List<Climber> climbers;

    public ClimberRepositoryInMemoryImpl() {
        climbers = new ArrayList<>();
        climbers.add(new Climber(1, "Ivana", "Samardzija Ostojic", 1984));
        climbers.add(new Climber(2, "Stasa", "Gejo", 1997));
    }

    public List<Climber> getClimbers() {
        return climbers;
    }

    @Override
    public List<Climber> getAll() {
        return climbers;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Climber> climber = climbers.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (climber.isPresent()){
            climbers.remove(climber.get());
        }
    }

    @Override
    public void delete(Climber climber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Climber climber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Climber findById(Integer climber) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Climber insert(Climber climber) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
