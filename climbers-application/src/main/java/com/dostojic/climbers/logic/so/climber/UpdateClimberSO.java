/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.climber;

import com.dostojic.climbers.logic.so.template.GeneralUpdateSO;
import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.logic.ClimberRepository;
import com.dostojic.climbers.logic.TransactionManager;

/**
 *
 * @author Dejan.Ostojic
 */
public class UpdateClimberSO extends GeneralUpdateSO<Climber, Boolean>{
    ClimberRepository climberRepository;
    
    public UpdateClimberSO(TransactionManager transactionManager, ClimberRepository climberRepository) {
        super(transactionManager);
        this.climberRepository = climberRepository;
        
    }

    @Override
    protected void checkPrecondition(Climber domainObject) throws Exception {
//
        // no preconditions but could be something like
        // repository.getAll();
        System.out.println("no checking preconditions for update climber");
    }

    @Override
    protected Boolean executeOperation(Climber climber) throws Exception {
        System.out.println("DEBUG: Updating climber: " + climber);
        return climberRepository.update(climber);
    }

}
