/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.climber;

import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.logic.ClimberRepository;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.template.GeneralReportingSO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Dejan.Ostojic
 */
public class GetClimberDetailsSO extends GeneralReportingSO<Integer, Climber>{

    private final ClimberRepository climberRepository;
    
    public GetClimberDetailsSO(TransactionManager transactionManager, ClimberRepository climberRepository) {
        super(transactionManager);
        this.climberRepository = climberRepository;
    }


    @Override
    protected Climber executeOperation(Integer id) throws Exception {
        return climberRepository.findById(id);
    }

}
    