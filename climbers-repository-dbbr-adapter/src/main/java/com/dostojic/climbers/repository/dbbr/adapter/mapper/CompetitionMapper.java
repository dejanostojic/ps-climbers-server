/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter.mapper;

import com.dostojic.climbers.repository.dbbr.adapter.CompetitionDto;
import com.dostojic.climbers.repository.dbbr.adapter.RegistrationFeeDto;
import com.dostojic.climbers.repository.dbbr.adapter.RouteDto;
import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.RegistrationFee;
import com.dostojic.climbers.domain.Route;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
    
    List<Competition> toCompetitions(Collection<CompetitionDto> competitinDtos);

    @Mapping(source = "id.competitionId", target = "competition.id")
    @Mapping(source = "id.ord", target = "ord")
    Route fromDto(RouteDto routeDto);
    
    
    @Mapping(source = "competition.id", target = "id.competitionId")
    @Mapping(source = "ord", target = "id.ord")
    RouteDto toDto(Route route);
    
    @Mapping(source = "id.competitionId", target = "competition.id")
    @Mapping(source = "id.ord", target = "ord")
    RegistrationFee fromDto(RegistrationFeeDto registrationFeeDto);
    
    @Mapping(source = "competition.id", target = "id.competitionId")
    @Mapping(source = "ord", target = "id.ord")
    RegistrationFeeDto toDto(RegistrationFee registrationFee);
}
