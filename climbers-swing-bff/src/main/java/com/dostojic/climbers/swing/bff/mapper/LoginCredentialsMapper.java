/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.swing.bff.mapper;

import com.dostojic.climbers.common.dto.LoginCredentialsDto;
import com.dostojic.climbers.domain.valueobject.LoginCredentials;

/**
 *
 * @author planina
 */

public class LoginCredentialsMapper {
    
    public static LoginCredentialsMapper INSTANCE = new LoginCredentialsMapper();
    
    public LoginCredentials fromDto(LoginCredentialsDto dto){
        return new LoginCredentials(dto.getUsername(), dto.getPassword());
    }
}
