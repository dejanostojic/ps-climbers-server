/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbBroker;
import com.dostojic.climbers.domain.Route;
import com.dostojic.climbers.repository.RouteRepository;
import com.dostojic.climbers.repository.dbbr.adapter.mapper.CompetitionMapper;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public class RouteRepositoryDbbrImpl implements RouteRepository{
    private final DbBroker<RouteDto, RouteCompositeId> routeDbbr;

    public RouteRepositoryDbbrImpl() {
        this.routeDbbr = new DbBroker<RouteDto, RouteCompositeId> (RouteDto.class){};
    }

    @Override
    public Route insert(Route route) throws Exception {
        RouteDto routeDto = CompetitionMapper.INSTANCE.toDto(route);
        System.out.println("ROUTE DTO: " + routeDto);
        RouteDto inserted = routeDbbr.insert(routeDto);

        return CompetitionMapper.INSTANCE.fromDto(inserted);
    }

    @Override
    public Route update(Route route) throws Exception {
        RouteDto routeDto = CompetitionMapper.INSTANCE.toDto(route);
        System.out.println("ROUTE DTO: " + routeDto);
        boolean updated = routeDbbr.update(routeDto);
        if (!updated){
            throw new Exception("Route can not be updated! " + route);
        }
        return route;    
    }

    @Override
    public void delete(Route route) throws Exception {
        RouteDto routeDto = CompetitionMapper.INSTANCE.toDto(route);
        System.out.println("ROUTE DTO: " + routeDto);
        boolean deleted = routeDbbr.delete(routeDto);
        if (!deleted){
            throw new Exception("Route can not be deleted! " + route);
        }
    }

    @Override
    public List<Route> findByCompetitionId(Integer competitionId) throws Exception {
        return CompetitionMapper.INSTANCE.toRoutes(routeDbbr.loadList("competition_id = " + competitionId, null));
    }
    
}
