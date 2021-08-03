/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.competition;

import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.valueobject.CompetitionSearchCriteria;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.template.GeneralReportingSO;
import com.dostojic.climbers.repository.CompetitionRepository;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public class SearchCompetitions extends GeneralReportingSO<CompetitionSearchCriteria, List<Competition>>{

     private final CompetitionRepository competitionRepository;

    public SearchCompetitions(TransactionManager transactionManager, CompetitionRepository competitionRepository) {
        super(transactionManager);
        this.competitionRepository = competitionRepository;
    }
     

    @Override
    protected List<Competition> executeOperation(CompetitionSearchCriteria searchCriteria) throws Exception {
        return competitionRepository.searchCompetitions(searchCriteria);
    }
    
}
