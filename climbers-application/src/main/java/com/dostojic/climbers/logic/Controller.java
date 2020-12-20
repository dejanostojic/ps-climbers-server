/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic;

import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.domain.User;
import com.dostojic.climbers.domain.valueobject.LoginCredentials;
import com.dostojic.climbers.logic.so.UserLoginSO;
import com.dostojic.climbers.logic.so.climber.CreateClimberSO;
import com.dostojic.climbers.logic.so.climber.DeleteClimberSO;
import com.dostojic.climbers.logic.so.climber.GetAllClimbersSO;
import com.dostojic.climbers.logic.so.climber.GetClimberDetailsSO;
import com.dostojic.climbers.logic.so.climber.UpdateClimberSO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author planina
 */
public class Controller {

    private final UserLoginSO userLoginSO;

    // Climber related system operations
    private final GetAllClimbersSO allClimbersSO;
    private final GetClimberDetailsSO climberDetailsSO;
    private final UpdateClimberSO updateClimberSo;
    private final DeleteClimberSO deleteClimberSO;
    private final CreateClimberSO createClimberSO;


    public Controller(TransactionManager transactionManager, ClimberRepository climberRepository, CompetitionRepository competitionRepository, UserRepository userRepository) {
        
        this.userLoginSO = new UserLoginSO(transactionManager, userRepository);
        // Climber related system operations
        this.allClimbersSO = new GetAllClimbersSO(transactionManager, climberRepository);
        this.climberDetailsSO = new GetClimberDetailsSO(transactionManager, climberRepository);
        this.updateClimberSo = new UpdateClimberSO(transactionManager, climberRepository);
        this.deleteClimberSO = new DeleteClimberSO(transactionManager, climberRepository);
        this.createClimberSO = new CreateClimberSO(transactionManager, climberRepository);
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
    
}
