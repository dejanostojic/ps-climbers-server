/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.competition;

import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.repository.CompetitionRepository;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.template.GeneralUpdateSO;

/**
 *
 * @author Dejan.Ostojic
 */
public class UpdateCompetition extends GeneralUpdateSO<Competition, Competition>{

    private final CompetitionRepository competitionRepository;
    
    public UpdateCompetition(TransactionManager transactionManager, CompetitionRepository competitionRepository) {
        super(transactionManager);
        this.competitionRepository = competitionRepository;
    }

    @Override
    protected void checkPrecondition(Competition domainObject) throws Exception {
        System.out.println("TODO: CHECK PRECONDITIONS FOR UPDATE COMPETITION");
    }

    @Override
    protected Competition executeOperation(Competition competition) throws Exception {
        return competitionRepository.update(competition);
    }
    
}
