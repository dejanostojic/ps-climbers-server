/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.boot;

import com.dostojic.climbers.logic.Controller;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.so.UserLoginSO;
import com.dostojic.climbers.logic.so.climber.CreateClimber;
import com.dostojic.climbers.logic.so.climber.DeleteClimber;
import com.dostojic.climbers.logic.so.climber.FindClimber;
import com.dostojic.climbers.logic.so.climber.SearchClimbers;
import com.dostojic.climbers.logic.so.climber.UpdateClimber;
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
                new FindClimber(tm, climberRepository),
                new UpdateClimber(tm, climberRepository),
                new DeleteClimber(tm, climberRepository),
                new CreateClimber(tm, climberRepository),
                new SaveCompetition(tm, competitionRepository, routeRepository, registrationFeeRepository, registrationRepository),
                new UpdateCompetition(tm, competitionRepository, routeRepository, registrationFeeRepository, registrationRepository),
                new SearchCompetitions(tm, competitionRepository),
                new GetCompetitionDetailsSO(tm, competitionRepository, routeRepository, registrationFeeRepository, registrationRepository));

    }
}
