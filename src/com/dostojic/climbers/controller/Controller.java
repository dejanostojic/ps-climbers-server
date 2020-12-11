/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.controller;

import com.dostojic.climbers.exception.LoginException;
import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.logic.ClimberRepository;
import com.dostojic.climbers.logic.CompetitionRepository;
import com.dostojic.climbers.domain.User;
import com.dostojic.climbers.logic.UserRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author planina
 */
public class Controller {

    private final ClimberRepository climberRepository;
    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;

//    private static Controller instance;
    
/*    private Controller() {
        climberRepository = new ClimberRepositoryInMemoryImpl();
        competitionRepository = new CompetitionRepositoryInMemoryImpl();
        userRepository = new UserRepositoryInMemoryImpl();
    }
*/
    public Controller(ClimberRepository climberRepository, CompetitionRepository competitionRepository, UserRepository userRepository) {
        this.climberRepository = climberRepository;
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
    }
    
    
    
//    public static Controller getInstance() {
//        if (instance == null) {
//            instance = new Controller();
//        }
//        return instance;
//    }


    public User login(String username, String password) throws LoginException {
       List<User> users = userRepository.getAll();
        
        for (User user : users) {
            if (user.getUsername().equals(username)) {
              if (user.getPassword().equals(password)){
                  return user;
              }  else {
                  throw new LoginException("Inccorect password");
              }
            } 
        }
        
        throw new LoginException("Inccorect username");
    }
    
    public List<Climber> getAllClimbers(){
        return climberRepository.getAll();
    }
    
    public Optional<Climber> findClimberById(Integer id){
        return climberRepository.getAll()
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
    
    public void deleteClimberById(Integer id){
        climberRepository.deleteById(id);
    }
    
}
