/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbBroker;
import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.logic.CompetitionRepository;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public class CompetitionRepositoryDbbrImpl implements CompetitionRepository{

    DbBroker<CompetitionDto, Long> dbbr;

    public CompetitionRepositoryDbbrImpl() {
        this.dbbr = new DbBroker<CompetitionDto, Long> (CompetitionDto.class){};
    }
    
    @Override
    public List<Competition> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
