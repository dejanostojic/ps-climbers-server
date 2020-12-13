/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic;

import com.dostojic.climbers.domain.Climber;
import java.util.List;

/**
 *
 * @author planina
 */
public interface ClimberRepository {
    List<Climber> getAll();
    void deleteById(Integer id);
}
