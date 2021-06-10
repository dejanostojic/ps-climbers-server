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
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dejan.Ostojic
 */
public class UpdateCompetition extends GeneralUpdateSO<Competition, Competition> {

    private final CompetitionRepository competitionRepository;
    private final RouteRepository routeRepository;
    private final RegistrationFeeRepository registrationFeeRepository;
    private final RegistrationRepository registrationRepository;

    public UpdateCompetition(TransactionManager transactionManager, CompetitionRepository competitionRepository,
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
        System.out.println("TODO: CHECK PRECONDITIONS FOR UPDATE COMPETITION");
    }

    @Override
    protected Competition executeOperation(Competition competition) throws Exception {
        competitionRepository.update(competition);

        handleRoutes(competition);
        handleRegistrationFees(competition);
        handleRegistrations(competition);

        return competition; // TODO CHECK Returned object
    }

    private void handleRoutes(Competition competition) throws Exception {
        List<Route> prevRoutes = routeRepository.findByCompetitionId(competition.getId());
        List<Route> submittedRoutes = competition.getRoutes();

        List<Route> deletedRoutes = findDeletedRoutes(prevRoutes, submittedRoutes);
        List<Route> updatedRoutes = findUpdatedRoutes(prevRoutes, submittedRoutes);
        List<Route> createdRoutes = findCreatedRoutes(prevRoutes, submittedRoutes);

        for (Route r : deletedRoutes) {
            routeRepository.delete(r);
        }

        for (Route r : updatedRoutes) {
            routeRepository.update(r);
        }

        for (Route r : createdRoutes) {
            routeRepository.insert(r);
        }

    }


    private List<Route> findDeletedRoutes(List<Route> prevRoutes, List<Route> submittedRoutes) {
        return prevRoutes.stream().filter(prev -> !submittedRoutes.contains(prev)).collect(Collectors.toList());
    }
    
    private List<Route> findUpdatedRoutes(List<Route> prevRoutes, List<Route> submittedRoutes) {
        return submittedRoutes.stream().filter(submitted -> prevRoutes.contains(submitted)).collect(Collectors.toList());
    }

    private List<Route> findCreatedRoutes(List<Route> prevRoutes, List<Route> submittedRoutes) {
        return submittedRoutes.stream().filter(submitted -> !prevRoutes.contains(submitted)).collect(Collectors.toList());
    }


    private void handleRegistrationFees(Competition competition) throws Exception {
        List<RegistrationFee> prevFees = registrationFeeRepository.findByCompetitionId(competition.getId());
        List<RegistrationFee> submittedFees = competition.getRegistrationFees();

        List<RegistrationFee> deletedRoutes = findDeletedRegistrationFees(prevFees, submittedFees);
        List<RegistrationFee> updatedRoutes = findUpdatedRegistrationFees(prevFees, submittedFees);
        List<RegistrationFee> createdRoutes = findCreatedRegistrationFees(prevFees, submittedFees);

        for (RegistrationFee r : deletedRoutes) {
            registrationFeeRepository.delete(r);
        }

        for (RegistrationFee r : updatedRoutes) {
            registrationFeeRepository.update(r);
        }

        for (RegistrationFee r : createdRoutes) {
            registrationFeeRepository.insert(r);
        }
    }

    private List<RegistrationFee> findDeletedRegistrationFees(List<RegistrationFee> prevRoutes, List<RegistrationFee> submittedRoutes) {
        return prevRoutes.stream().filter(prev -> !submittedRoutes.contains(prev)).collect(Collectors.toList());
    }
    
    private List<RegistrationFee> findUpdatedRegistrationFees(List<RegistrationFee> prevRoutes, List<RegistrationFee> submittedRoutes) {
        return submittedRoutes.stream().filter(submitted -> prevRoutes.contains(submitted)).collect(Collectors.toList());
    }

    private List<RegistrationFee> findCreatedRegistrationFees(List<RegistrationFee> prevRoutes, List<RegistrationFee> submittedRoutes) {
        return submittedRoutes.stream().filter(submitted -> !prevRoutes.contains(submitted)).collect(Collectors.toList());
    }
    
    private void handleRegistrations(Competition competition) throws Exception {
        List<Registration> prevRoutes = registrationRepository.findByCompetitionId(competition.getId());
        List<Registration> submittedRoutes = competition.getRegistrations();

        List<Registration> deletedRoutes = findDeletedRegistrations(prevRoutes, submittedRoutes);
        List<Registration> updatedRoutes = findUpdatedRegistrations(prevRoutes, submittedRoutes);
        List<Registration> createdRoutes = findCreatedRegistrations(prevRoutes, submittedRoutes);

        for (Registration r : deletedRoutes) {
            registrationRepository.delete(r);
        }

        for (Registration r : updatedRoutes) {
            registrationRepository.update(r);
        }

        for (Registration r : createdRoutes) {
            registrationRepository.insert(r);
        }
    }
    
    private List<Registration> findDeletedRegistrations(List<Registration> prevRoutes, List<Registration> submittedRoutes) {
        return prevRoutes.stream().filter(prev -> !submittedRoutes.contains(prev)).collect(Collectors.toList());
    }
    
    private List<Registration> findUpdatedRegistrations(List<Registration> prevRoutes, List<Registration> submittedRoutes) {
        return submittedRoutes.stream().filter(submitted -> prevRoutes.contains(submitted)).collect(Collectors.toList());
    }

    private List<Registration> findCreatedRegistrations(List<Registration> prevRoutes, List<Registration> submittedRoutes) {
        return submittedRoutes.stream().filter(submitted -> !prevRoutes.contains(submitted)).collect(Collectors.toList());
    }


}
