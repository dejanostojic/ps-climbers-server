/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic;

import com.dostojic.climbers.repository.CompetitionRepository;
import com.dostojic.climbers.repository.ClimberRepository;
import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.User;
import com.dostojic.climbers.domain.valueobject.CompetitionSearchCriteria;
import com.dostojic.climbers.domain.valueobject.LoginCredentials;
import com.dostojic.climbers.logic.so.UserLoginSO;
import com.dostojic.climbers.logic.so.climber.CreateClimberSO;
import com.dostojic.climbers.logic.so.climber.DeleteClimberSO;
import com.dostojic.climbers.logic.so.climber.GetAllClimbersSO;
import com.dostojic.climbers.logic.so.climber.GetClimberDetailsSO;
import com.dostojic.climbers.logic.so.climber.UpdateClimberSO;
import com.dostojic.climbers.logic.so.competition.GetCompetitionDetailsSO;
import com.dostojic.climbers.logic.so.competition.SaveCompetition;
import com.dostojic.climbers.logic.so.competition.SearchCompetitions;
import com.dostojic.climbers.logic.so.competition.UpdateCompetition;
import com.dostojic.climbers.logic.so.template.GeneralSO;
import com.dostojic.climbers.repository.UserRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author planina
 */
public class Controller {

    private final GeneralSO<LoginCredentials, User> userLoginSO;

    // Climber related system operations
    private final GetAllClimbersSO allClimbersSO;
    private final GetClimberDetailsSO climberDetailsSO;
    private final UpdateClimberSO updateClimberSo;
    private final DeleteClimberSO deleteClimberSO;
    private final CreateClimberSO createClimberSO;

    // Competition related SO
    private final SaveCompetition saveCompetitionSO;
    private final UpdateCompetition updateCompetition;
    private final SearchCompetitions searchCompetitions;
    private final GetCompetitionDetailsSO competitionDetails;

    public Controller(TransactionManager transactionManager, ClimberRepository climberRepository, 
            CompetitionRepository competitionRepository, UserRepository userRepository) {
        
        this.userLoginSO = new UserLoginSO(transactionManager, userRepository);
        // Climber related system operations
        this.allClimbersSO = new GetAllClimbersSO(transactionManager, climberRepository);
        this.climberDetailsSO = new GetClimberDetailsSO(transactionManager, climberRepository);
        this.updateClimberSo = new UpdateClimberSO(transactionManager, climberRepository);
        this.deleteClimberSO = new DeleteClimberSO(transactionManager, climberRepository);
        this.createClimberSO = new CreateClimberSO(transactionManager, climberRepository);
        
        // competition relatedSo
        this.saveCompetitionSO = new SaveCompetition(transactionManager, competitionRepository);
        this.updateCompetition = new UpdateCompetition(transactionManager, competitionRepository);
        this.searchCompetitions = new SearchCompetitions(transactionManager, competitionRepository);
        this.competitionDetails = new GetCompetitionDetailsSO(transactionManager, competitionRepository);
    }

    public User login(LoginCredentials credentials) throws Exception {
        return userLoginSO.execute(credentials);
    }
    
    public List<Climber> getAllClimbers() throws Exception{
        return allClimbersSO.execute(null);
    }
    
    public Climber findClimberById(Integer id) throws Exception{
        return climberDetailsSO.execute(id);
    }
    
    public void deleteClimberById(Integer id) throws Exception{
        deleteClimberSO.execute(id);
    }
    
    public void updateClimber(Climber climber) throws Exception{
        updateClimberSo.execute(climber);
    }

    public Climber createClimber(Climber climber) throws Exception {
        return createClimberSO.execute(climber);
    }

    public Competition saveCompetition(Competition competition) throws Exception {
        return saveCompetitionSO.execute(competition);
    }
    
    public List<Competition> searchCompetitions(CompetitionSearchCriteria searchCriteria) throws Exception{
        return searchCompetitions.execute(searchCriteria);
    }

    public Competition findCompetitionById(Integer id) throws Exception{
        return competitionDetails.execute(id);
    }
    
    public Competition updateCompetition(Competition competition) throws Exception {
        return updateCompetition.execute(competition);
    }
}
