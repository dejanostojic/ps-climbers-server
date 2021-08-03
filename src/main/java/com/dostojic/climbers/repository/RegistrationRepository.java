/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository;

import com.dostojic.climbers.domain.Registration;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public interface RegistrationRepository {
    Registration insert(Registration registration) throws Exception;
    Registration update(Registration registration) throws Exception;
    void delete(Registration registration) throws Exception;

    public List<Registration> findByCompetitionId(Integer id) throws Exception;
}
