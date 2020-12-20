/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so;

import com.dostojic.climbers.domain.User;
import com.dostojic.climbers.domain.valueobject.LoginCredentials;
import com.dostojic.climbers.exception.LoginException;
import com.dostojic.climbers.logic.TransactionManager;
import com.dostojic.climbers.logic.UserRepository;
import com.dostojic.climbers.logic.so.template.GeneralReportingSO;

/**
 * System operation for login functionality. 
 * @author Dejan.Ostojic
 */
public class UserLoginSO extends GeneralReportingSO<LoginCredentials, User>{
    
    private final UserRepository userRepository;
    
    public UserLoginSO(TransactionManager transactionManager, UserRepository userRepository) {
        super(transactionManager);
        this.userRepository = userRepository;
    }

    @Override
    protected User executeOperation(LoginCredentials loginCreds) throws Exception {
        String username = loginCreds.getUsername();
        String pass = loginCreds.getPassword();
        
        User user = userRepository.findByUsernameAndPassword(username, pass);
        if (user == null){
            throw new LoginException("Wrong username of password");
        }
        return user;
    }
    
}
