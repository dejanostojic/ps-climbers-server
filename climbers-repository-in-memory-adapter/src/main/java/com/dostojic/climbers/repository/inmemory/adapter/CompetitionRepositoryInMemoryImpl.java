/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.inmemory.adapter;

import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.valueobject.CompetitionSearchCriteria;
import com.dostojic.climbers.repository.CompetitionRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author planina
 */
public class CompetitionRepositoryInMemoryImpl implements CompetitionRepository {

    private List<Competition> competitions;

    public CompetitionRepositoryInMemoryImpl() {
        competitions = new ArrayList<>();
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    @Override
    public Competition insert(Competition competition) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Competition update(Competition competition) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Competition> searchCompetitions(CompetitionSearchCriteria searchCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Competition findById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
