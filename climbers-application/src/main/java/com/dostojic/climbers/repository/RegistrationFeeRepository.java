/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository;

import com.dostojic.climbers.domain.RegistrationFee;

/**
 *
 * @author Dejan.Ostojic
 */
public interface RegistrationFeeRepository {
    
    RegistrationFee save(RegistrationFee registrationFee) throws Exception;
    
    RegistrationFee update(RegistrationFee registrationFee) throws Exception;

}
