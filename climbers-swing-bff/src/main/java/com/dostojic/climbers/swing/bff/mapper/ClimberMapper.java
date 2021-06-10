/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.swing.bff.mapper;

import com.dostojic.climbers.common.dto.ClimberDto;
import com.dostojic.climbers.common.dto.ClimberSearchCriteriaDto;
import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.domain.valueobject.ClimberSearchCriteria;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author planina
 */
@Mapper
public interface ClimberMapper {
    
    public static ClimberMapper INSTANCE = Mappers.getMapper(ClimberMapper.class);

    ClimberDto toDto(Climber climber);
    Climber fromDto(ClimberDto climber);
    
    ClimberSearchCriteria toSearchCriteria(ClimberSearchCriteriaDto searchCriteriaDto);
    
    List<ClimberDto> toDto(Collection<Climber> climberList);
    
}
