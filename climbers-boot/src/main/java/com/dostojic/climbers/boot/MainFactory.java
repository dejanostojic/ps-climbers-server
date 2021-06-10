/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.boot;

import com.dostojic.climbers.logic.Controller;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.UserLoginSO;
import com.dostojic.climbers.logic.so.climber.CreateClimberSO;
import com.dostojic.climbers.logic.so.climber.DeleteClimberSO;
import com.dostojic.climbers.logic.so.climber.GetClimberDetailsSO;
import com.dostojic.climbers.logic.so.climber.SearchClimbers;
import com.dostojic.climbers.logic.so.climber.UpdateClimberSO;
import com.dostojic.climbers.logic.so.competition.GetCompetitionDetailsSO;
import com.dostojic.climbers.logic.so.competition.SaveCompetition;
import com.dostojic.climbers.logic.so.competition.SearchCompetitions;
import com.dostojic.climbers.logic.so.competition.UpdateCompetition;
import com.dostojic.climbers.repository.ClimberRepository;
import com.dostojic.climbers.repository.CompetitionRepository;
import com.dostojic.climbers.repository.RegistrationFeeRepository;
import com.dostojic.climbers.repository.RegistrationRepository;
import com.dostojic.climbers.repository.RouteRepository;
import com.dostojic.climbers.repository.UserRepository;
import com.dostojic.climbers.repository.dbbr.adapter.ClimberRepositoryDbbrImpl;
import com.dostojic.climbers.repository.dbbr.adapter.CompetitionRepositoryDbbrImpl;
import com.dostojic.climbers.repository.dbbr.adapter.RegistrationFeeRepositoryDbbrImpl;
import com.dostojic.climbers.repository.dbbr.adapter.RegistrationRepositoryDbbrImpl;
import com.dostojic.climbers.repository.dbbr.adapter.RouteRepositoryDbbrImpl;
import com.dostojic.climbers.repository.dbbr.adapter.TransactionManagerImpl;
import com.dostojic.climbers.repository.dbbr.adapter.UserRepositoryDbbrImpl;

/**
 *
 * @author planina
 */
public class MainFactory {

    public static Controller getController() {
        TransactionManager tm = new TransactionManagerImpl();
        ClimberRepository climberRepository = new ClimberRepositoryDbbrImpl();
        CompetitionRepository competitionRepository = new CompetitionRepositoryDbbrImpl();
        UserRepository userRepository = new UserRepositoryDbbrImpl();
        RouteRepository routeRepository = new RouteRepositoryDbbrImpl();
        RegistrationFeeRepository registrationFeeRepository = new RegistrationFeeRepositoryDbbrImpl();
        RegistrationRepository registrationRepository = new RegistrationRepositoryDbbrImpl();

        /*
        return new Controller(new ClimberRepositoryInMemoryImpl(),
                new CompetitionRepositoryInMemoryImpl(), 
                new UserRepositoryInMemoryImpl());
         */
//        return new Controller(new TransactionManagerImpl(),
//                new ClimberRepositoryDbbrImpl(),
//                new CompetitionRepositoryDbbrImpl(),
//                new UserRepositoryDbbrImpl());
        return new Controller(new UserLoginSO(tm, userRepository),
                new SearchClimbers(tm, climberRepository),
                new GetClimberDetailsSO(tm, climberRepository),
                new UpdateClimberSO(tm, climberRepository),
                new DeleteClimberSO(tm, climberRepository),
                new CreateClimberSO(tm, climberRepository),
                new SaveCompetition(tm, competitionRepository, routeRepository, registrationFeeRepository, registrationRepository),
                new UpdateCompetition(tm, competitionRepository, routeRepository, registrationFeeRepository, registrationRepository),
                new SearchCompetitions(tm, competitionRepository),
                new GetCompetitionDetailsSO(tm, competitionRepository, routeRepository, registrationFeeRepository, registrationRepository));

    }
}
