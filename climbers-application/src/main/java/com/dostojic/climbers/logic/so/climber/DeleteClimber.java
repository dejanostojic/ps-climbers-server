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
public class DeleteClimber extends GeneralUpdateSO<Integer, Boolean> {

    private final ClimberRepository climberRepository;

    public DeleteClimber(TransactionManager transactionManager, ClimberRepository climberRepository) {
        super(transactionManager);
        this.climberRepository = climberRepository;
    }

    @Override
    protected void checkPrecondition(Integer climberId) throws Exception {
        System.out.println("No preconditions DeleteClimberSO");
    }

    @Override
    protected Boolean executeOperation(Integer climberId) throws Exception {
        climberRepository.deleteById(climberId);
        System.out.println("TODO: return boolean if climber deleted!");
        return null;
    }

}
