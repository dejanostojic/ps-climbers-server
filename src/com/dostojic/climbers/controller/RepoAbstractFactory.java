/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.controller;

import com.dostojic.climbers.logic.ClimberRepository;
import com.dostojic.climbers.logic.CompetitionRepository;
import com.dostojic.climbers.logic.UserRepository;

/**
 *
 * @author planina
 */
public interface RepoAbstractFactory {
    ClimberRepository getClimberRepository();
    CompetitionRepository getCompetitionRepository();
    UserRepository getUserRepository();

}
