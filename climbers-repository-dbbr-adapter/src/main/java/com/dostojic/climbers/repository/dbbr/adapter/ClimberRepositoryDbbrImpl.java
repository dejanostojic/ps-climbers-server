/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbBroker;
import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.logic.ClimberRepository;
import com.dostojic.climbers.repository.dbbr.adapter.mapper.ClimberMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dejan.Ostojic
 */
public class ClimberRepositoryDbbrImpl implements ClimberRepository {

    private DbBroker<ClimberDto, Integer> dbbr = new DbBroker<ClimberDto, Integer>(ClimberDto.class) {
    };
    private ClimberMapper mapper = ClimberMapper.INSTANCE;

    @Override
    public List<Climber> getAll() throws Exception {
        return mapper.fromDto(dbbr.loadAll());
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        dbbr.deleteByPk(id);
    }

    @Override
    public void delete(Climber climber) throws Exception {
        dbbr.delete(mapper.toDto(climber));
    }

    @Override
    public boolean update(Climber climber) throws Exception {
        return dbbr.update(mapper.toDto(climber));
    }

    @Override
    public Climber findById(Integer climberId) throws Exception {
        return mapper.fromDto(dbbr.loadByPk(climberId));
    }

    @Override
    public Climber insert(Climber climber) throws Exception {
        try {
            return mapper.fromDto(dbbr.insert(
                    mapper.toDto(climber)));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error saving climber.", ex);
        }
    }

}
