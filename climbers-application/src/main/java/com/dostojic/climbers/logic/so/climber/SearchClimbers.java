/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.climber;

import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.domain.valueobject.ClimberSearchCriteria;
import com.dostojic.climbers.repository.ClimberRepository;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.template.GeneralReportingSO;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public class SearchClimbers extends GeneralReportingSO<ClimberSearchCriteria, List<Climber>>{
    private ClimberRepository climberRepository;
    
    public SearchClimbers(TransactionManager transactionManager, ClimberRepository climberRepository) {
        super(transactionManager);
        this.climberRepository = climberRepository;
    }


    @Override
    protected List<Climber> executeOperation(ClimberSearchCriteria searchCriteria) throws Exception {
        return climberRepository.searchClimbers(searchCriteria);
    }
    
}
