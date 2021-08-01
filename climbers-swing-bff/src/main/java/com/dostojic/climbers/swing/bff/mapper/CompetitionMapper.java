/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.swing.bff.mapper;

import com.dostojic.climbers.common.dto.CompetitionDto;
import com.dostojic.climbers.common.dto.CompetitionSearchCriteriaDto;
import com.dostojic.climbers.common.dto.RegistrationFeeDto;
import com.dostojic.climbers.common.dto.RouteDto;
import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.RegistrationFee;
import com.dostojic.climbers.domain.Route;
import com.dostojic.climbers.domain.valueobject.CompetitionSearchCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Dejan.Ostojic
 */
@Mapper
public interface CompetitionMapper {
    
    public static CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    Competition fromDto(CompetitionDto competitionDto);
    CompetitionDto toDto(Competition competition);
    
    CompetitionSearchCriteria toSearchCriteria(CompetitionSearchCriteriaDto searchCriteria);
    
    Route fromDto(RouteDto routeDto);
    RouteDto toDto(Route route);
    
    RegistrationFee fromDto(RegistrationFeeDto registrationFeeDto);
    RegistrationFeeDto toDto(RegistrationFee registrationFee);
}
