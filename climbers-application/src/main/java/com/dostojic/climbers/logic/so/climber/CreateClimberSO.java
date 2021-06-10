/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.climber;

import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.repository.ClimberRepository;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.template.GeneralUpdateSO;

/**
 *
 * @author Dejan.Ostojic
 */
public class CreateClimberSO extends GeneralUpdateSO<Climber, Climber> {

    private final ClimberRepository climberRepository;

    public CreateClimberSO(TransactionManager transactionManager, ClimberRepository climberRepository) {
        super(transactionManager);
        this.climberRepository = climberRepository;
    }

    @Override
    protected void checkPrecondition(Climber climber) throws Exception {
        new ClimberValidator().validate(climber);
    }

    @Override
    protected Climber executeOperation(Climber climber) throws Exception {
        System.out.println("DEBUG: Inserting climber: " + climber);

        return climberRepository.insert(climber);
    }

}
