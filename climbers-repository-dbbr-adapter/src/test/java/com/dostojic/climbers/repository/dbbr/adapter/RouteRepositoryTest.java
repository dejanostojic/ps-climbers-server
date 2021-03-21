/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbBroker;
import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.RegistrationFee;
import com.dostojic.climbers.domain.Route;
import com.dostojic.climbers.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Dejan.Ostojic
 */
public class RouteRepositoryTest {
    private TransactionManagerImpl transactionManagerImpl;
    private DbBroker<RouteDto, RouteCompositeId> routeDbbr;

    
    private void whenGetAllExecutedResutlsAreFine() throws Exception {
        transactionManagerImpl = new TransactionManagerImpl();
        transactionManagerImpl.startTransaction();
        CompetitionRepositoryDbbrImpl competitionRepositoryDbbrImpl = new CompetitionRepositoryDbbrImpl();

        Competition c = new Competition(1, "name", "desc", new Date(), new Date(), new Date(), new ArrayList<Route>(), new ArrayList<RegistrationFee>());
        c.setRoutes(Arrays.asList(new Route(c, 1, "1", "1"), new Route(c, 2, "2", "2+")));
        
        competitionRepositoryDbbrImpl.insert(c);
//        
//        this.routeDbbr = new DbBroker<RouteDto, RouteCompositeId> (RouteDto.class){};
//        RouteDto routeDto = new RouteDto();
//        RouteCompositeId id = new RouteCompositeId();
//        id.setCompetitionId(1);
//        id.setOrd(1000);
//        routeDto.setId(id);
//        
//        routeDbbr.insert(routeDto);
//        RouteDto loadByPk = routeDbbr.loadByPk(id);
//        Assertions.assertNull(loadByPk);


        RouteCompositeId id = new RouteCompositeId();
        id.setCompetitionId(1);
        id.setOrd(1);
        RouteDto loadByPk = routeDbbr.loadByPk(id);
        Assertions.assertNotNull(loadByPk);

        transactionManagerImpl.commit();

        

    }
    
    
}
