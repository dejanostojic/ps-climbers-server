/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.competition;

import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.repository.CompetitionRepository;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.template.GeneralReportingSO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Dejan.Ostojic
 */
public class GetCompetitionDetailsSO extends GeneralReportingSO<Integer, Competition>{

    private final CompetitionRepository competitionRepository;
    
    public GetCompetitionDetailsSO(TransactionManager transactionManager, CompetitionRepository competitionRepository) {
        super(transactionManager);
        this.competitionRepository = competitionRepository;
    }


    @Override
    protected Competition executeOperation(Integer id) throws Exception {
        return competitionRepository.findById(id);
    }

}
    