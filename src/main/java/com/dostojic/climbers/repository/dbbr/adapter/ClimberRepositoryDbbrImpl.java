/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbBroker;
import com.dostojic.climbers.dbbr.improved.QueryUtils;
import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.domain.valueobject.ClimberSearchCriteria;
import com.dostojic.climbers.repository.ClimberRepository;
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
    public List<Climber> searchClimbers(ClimberSearchCriteria searchCriteria) throws Exception {
        StringBuilder where = new StringBuilder("true");
        
       if (searchCriteria.getFirstName()!= null && !searchCriteria.getFirstName().isEmpty()){
           where.append(" and first_name like").append(QueryUtils.mySqlLikeLiteral(searchCriteria.getFirstName()));
        }
       
        
       if (searchCriteria.getLastName()!= null && !searchCriteria.getLastName().isEmpty()){
           where.append(" and last_name like").append(QueryUtils.mySqlLikeLiteral(searchCriteria.getLastName()));
        }
       
        return mapper.fromDto(dbbr.loadList(where.toString(), "last_name, first_name"));
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
