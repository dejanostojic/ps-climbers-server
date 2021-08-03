/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter.mapper;

import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.Registration;
import com.dostojic.climbers.domain.RegistrationFee;
import com.dostojic.climbers.domain.Route;
import com.dostojic.climbers.repository.dbbr.adapter.CompetitionDto;
import com.dostojic.climbers.repository.dbbr.adapter.RouteCompositeId;
import com.dostojic.climbers.repository.dbbr.adapter.RouteDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Dejan.Ostojic
 */
public class CompetitionMapperTest {
    
    private final int COMPETITION_ID = 1; 
    private final String COMP_NAME = "Comp 1"; 
    private final String COMP_DESC = "Comp desc"; 
    private final Date COMP_REG_OPEN = new Date(); 
    private final Date COMP_REG_CLOSE = new Date(); 
    private final Date COMP_REG_EVENT_START = new Date(); 
    private final int ROUTE_ORD = 1; 
    private final String ROUTE_NAME = "Route 1"; 
    private final String ROUTE_GRADE = "8a"; 
    private final int FEE_ORD = 1; 
    private final String FEE_NAME = "Route 1"; 
    private final Date FEE_START = new Date(); 
    private final Date FEE_END = new Date(); 
    private final Competition COMPETITION = createCompetition();
    
    @Test
    public void testCompetitionMapperToDto(){
        CompetitionDto dto = CompetitionMapper.INSTANCE.toDto(COMPETITION);
        
        Assertions.assertEquals(COMPETITION_ID, dto.getId());
        Assertions.assertEquals(COMP_NAME, dto.getName());
        Assertions.assertEquals(COMP_DESC, dto.getDescription());
        Assertions.assertEquals(1, dto.getRoutes().size());
       /*
        Assertions.assertEquals(COMPETITION_ID, dto.getRoutes().get(0).getId().getCompetitionId());
        Assertions.assertEquals(ROUTE_ORD, dto.getRoutes().get(0).getId().getOrd());
        Assertions.assertEquals(1, dto.getRegistrationFees().size());
        Assertions.assertEquals(FEE_NAME, dto.getRegistrationFees().get(0).getName());
        Assertions.assertEquals(FEE_ORD, dto.getRegistrationFees().get(0).getOrd());
        Assertions.assertEquals(FEE_START, dto.getRegistrationFees().get(0).getStartDate());
        Assertions.assertEquals(FEE_END, dto.getRegistrationFees().get(0).getEndDate());
*/

    }
    
    @Test
    public void testRouteMapperFromDto(){
        RouteDto dto = createRouteDto1();
        
        Route route = CompetitionMapper.INSTANCE.fromDto(dto);
        
        Assertions.assertEquals(COMPETITION_ID, route.getCompetition().getId());
        Assertions.assertEquals(ROUTE_ORD, route.getOrd());
        Assertions.assertEquals(ROUTE_NAME, route.getName());
        Assertions.assertEquals(ROUTE_GRADE, route.getGrade());
    }
    
    @Test
    public void testRouteMapperToDto(){
        Route route = createRoute1();
        
        RouteDto routeDto = CompetitionMapper.INSTANCE.toDto(route);
        
        Assertions.assertEquals(COMPETITION_ID, routeDto.getId().getCompetitionId());
        Assertions.assertEquals(ROUTE_ORD, routeDto.getId().getOrd());
        Assertions.assertEquals(ROUTE_NAME, routeDto.getName());
        Assertions.assertEquals(ROUTE_GRADE, routeDto.getGrade());
    }
    
    private Competition createCompetition(){
        return new Competition(COMPETITION_ID, COMP_NAME, COMP_DESC, COMP_REG_OPEN,
        COMP_REG_CLOSE, COMP_REG_EVENT_START, Arrays.asList(createRoute1()), 
                Arrays.asList(createRegistrationFee1()), new ArrayList<>() );
    }
    
    private RouteDto createRouteDto1(){
        return new RouteDto(new RouteCompositeId(COMPETITION_ID, ROUTE_ORD), ROUTE_NAME, ROUTE_GRADE);
    }
    
    private Route createRoute1(){
        return new Route(COMPETITION, ROUTE_ORD, ROUTE_NAME, ROUTE_GRADE);
    }
    
    private RegistrationFee createRegistrationFee1(){
        return new RegistrationFee(COMPETITION, FEE_ORD, FEE_NAME, BigDecimal.ONE, FEE_START, FEE_END);
    }
    
}
