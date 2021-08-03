/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository;

import com.dostojic.climbers.domain.RegistrationFee;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public interface RegistrationFeeRepository {

    RegistrationFee insert(RegistrationFee route) throws Exception;

    RegistrationFee update(RegistrationFee registrationFee) throws Exception;

    void delete(RegistrationFee route) throws Exception;

    public List<RegistrationFee> findByCompetitionId(Integer id) throws Exception;

}
