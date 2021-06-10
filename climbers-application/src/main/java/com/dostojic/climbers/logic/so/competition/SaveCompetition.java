/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.competition;

import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.Registration;
import com.dostojic.climbers.domain.RegistrationFee;
import com.dostojic.climbers.domain.Route;
import com.dostojic.climbers.repository.CompetitionRepository;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.template.GeneralUpdateSO;
import com.dostojic.climbers.repository.RegistrationFeeRepository;
import com.dostojic.climbers.repository.RegistrationRepository;
import com.dostojic.climbers.repository.RouteRepository;

/**
 *
 * @author Dejan.Ostojic
 */
public class SaveCompetition extends GeneralUpdateSO<Competition, Competition>{

    private final CompetitionRepository competitionRepository;
    private final RouteRepository routeRepository;
    private final RegistrationFeeRepository registrationFeeRepository;
    private final RegistrationRepository registrationRepository;
    
    public SaveCompetition(TransactionManager transactionManager, CompetitionRepository competitionRepository,
            RouteRepository routeRepository, RegistrationFeeRepository registrationFeeRepository, 
            RegistrationRepository registrationRepository) {
        super(transactionManager);
        this.competitionRepository = competitionRepository;
        this.routeRepository = routeRepository;
        this.registrationFeeRepository = registrationFeeRepository;
        this.registrationRepository = registrationRepository;
    }
    @Override
    protected void checkPrecondition(Competition domainObject) throws Exception {
        System.out.println("TODO: CHECK PRECONDITIONS FOR SAVE COMPETITION");
    }

    @Override
    protected Competition executeOperation(Competition competition) throws Exception {
        Competition insertedCompetition = competitionRepository.insert(competition);

        insertRoutes(insertedCompetition);
        insertRegistrationFees(insertedCompetition);
        insertRegistrations(insertedCompetition);

        return insertedCompetition;
    }

    private void insertRoutes(Competition competition) throws Exception{
       for (Route r : competition.getRoutes()) {
            r.setCompetition(competition);
            routeRepository.insert(r);
        }    
    }

    private void insertRegistrationFees(Competition competition) throws Exception{
        for (RegistrationFee r : competition.getRegistrationFees()) {
            r.setCompetition(competition);
            registrationFeeRepository.insert(r);
        }    
    }

    private void insertRegistrations(Competition competition) throws Exception {
        for (Registration r : competition.getRegistrations()) {
            r.setCompetition(competition);
            registrationRepository.insert(r);
        }    
    }
    
    
}
