/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbBroker;
import com.dostojic.climbers.dbbr.improved.QueryUtils;
import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.valueobject.CompetitionSearchCriteria;
import com.dostojic.climbers.repository.CompetitionRepository;
import com.dostojic.climbers.repository.dbbr.adapter.mapper.CompetitionMapper;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public class CompetitionRepositoryDbbrImpl implements CompetitionRepository{

    DbBroker<CompetitionDto, Integer> dbbr;
//    DbBroker<RouteDto, RouteCompositeId> routeDbbr;
//    DbBroker<RegistrationFeeDto, RegistrationFeeCompositeId> registrationFeeDbbr;
//    DbBroker<RegistrationDto, RegistrationCompositeId> registrationDbbr;

    public CompetitionRepositoryDbbrImpl() {
        this.dbbr = new DbBroker<CompetitionDto, Integer> (CompetitionDto.class){};
//        this.routeDbbr = new DbBroker<RouteDto, RouteCompositeId> (RouteDto.class){};
//        this.registrationFeeDbbr = new DbBroker<RegistrationFeeDto, RegistrationFeeCompositeId> (RegistrationFeeDto.class){};
//        this.registrationDbbr = new DbBroker<RegistrationDto, RegistrationCompositeId> (RegistrationDto.class){};
    }
    
    @Override
    public Competition insert(Competition competition) throws Exception {
        CompetitionDto insert = dbbr.insert(CompetitionMapper.INSTANCE.toDto(competition));
        
//        for(Route route: competition.getRoutes()){
//            RouteDto routeDto = CompetitionMapper.INSTANCE.toDto(route);
//            
//            RouteCompositeId routeCompositeId = new RouteCompositeId();
//            System.out.println("Inserted id: " + insert.getId());
//            routeCompositeId.setCompetitionId(insert.getId());
//            routeCompositeId.setOrd(route.getOrd());
//            routeDto.setId(routeCompositeId);
//            routeDto.setGrade(route.getGrade());
//            routeDto.setName(route.getName());
//            System.out.println("inserting route: " + routeDto);
//            routeDbbr.insert(routeDto);
//
//        }
//        
//        for (RegistrationFee fee : competition.getRegistrationFees()){
//            RegistrationFeeCompositeId feeId = new RegistrationFeeCompositeId(insert.getId(), fee.getOrd());
//        
//            RegistrationFeeDto dto = CompetitionMapper.INSTANCE.toDto(fee);
//            dto.setId(feeId);
//            
//            System.out.println("inserting fee: " + dto);
//
//            registrationFeeDbbr.insert(dto);
//        }
        return CompetitionMapper.INSTANCE.fromDto(insert);
    }

    @Override
    public Competition update(Competition competition) throws Exception {
        // TODO FIX CODE DUPLICATION!!!
        boolean updated = dbbr.update(CompetitionMapper.INSTANCE.toDto(competition));
        if (!updated){
            throw new Exception("Competition that should be updated not found!"); // TODO: Throw proper bussines exception!
        }
        /*
        // delete all than insert all! TODO: Optimize only delete deleted and update updated!
        routeDbbr.delete("competition_id = " + competition.getId());
        for(Route route: competition.getRoutes()){
            RouteDto routeDto = CompetitionMapper.INSTANCE.toDto(route);
            
            RouteCompositeId routeCompositeId = new RouteCompositeId();
            System.out.println("Inserted id: " + competition.getId());
            routeCompositeId.setCompetitionId(competition.getId());
            routeCompositeId.setOrd(route.getOrd());
            routeDto.setId(routeCompositeId);
            routeDto.setGrade(route.getGrade());
            routeDto.setName(route.getName());
            System.out.println("inserting route: " + routeDto);
            routeDbbr.insert(routeDto);

        }
        registrationFeeDbbr.delete("competition_id = " + competition.getId());
        for (RegistrationFee fee : competition.getRegistrationFees()){
            RegistrationFeeCompositeId feeId = new RegistrationFeeCompositeId(competition.getId(), fee.getOrd());
        
            RegistrationFeeDto dto = CompetitionMapper.INSTANCE.toDto(fee);
            dto.setId(feeId);
            
            System.out.println("inserting fee: " + dto);

            registrationFeeDbbr.insert(dto);
        }
        
        registrationDbbr.delete("competition_id = " + competition.getId());

        for (Registration registration : competition.getRegistrations()){
            RegistrationCompositeId registrationId = new RegistrationCompositeId(competition.getId(), registration.getStartNumber());
        
            RegistrationDto dto = CompetitionMapper.INSTANCE.toDto(registration);
            dto.setId(registrationId);
            
            System.out.println("inserting fee: " + dto);

            registrationDbbr.insert(dto);
        }
        */
        return competition;
    }

    @Override
    public List<Competition> searchCompetitions(CompetitionSearchCriteria searchCriteria) throws Exception {
        StringBuilder where = new StringBuilder("true");
        
       if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()){
           where.append(" and name like").append(QueryUtils.mySqlLikeLiteral(searchCriteria.getName()));
        }
        
        
        List<CompetitionDto> competitions = dbbr.loadList(where.toString(), "name desc");
        
        return CompetitionMapper.INSTANCE.toCompetitions(competitions);
    }

    @Override
    public Competition findById(Integer id) throws Exception {
        
        Competition competition = CompetitionMapper.INSTANCE.fromDto(dbbr.loadByPk(id)); 
        
        return competition;
    }
    
    
}
