/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.BrokerBazePodataka;
import com.dostojic.climbers.dbbr.BrokerBazePodataka1;
import com.dostojic.climbers.dbbr.GeneralDObject;
import com.dostojic.climbers.dbbr.improved.DbBroker;
import static com.dostojic.climbers.dbbr.improved.QueryUtils.stringLiteral;
import com.dostojic.climbers.domain.User;
import com.dostojic.climbers.repository.UserRepository;
import com.dostojic.climbers.repository.dbbr.adapter.mapper.UserMapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author planina
 */
public class UserRepositoryDbbrImpl implements UserRepository {

    private DbBroker<UserDto, Long> broker;

    public UserRepositoryDbbrImpl() {
        this.broker = new DbBroker<UserDto, Long> (UserDto.class){};
    }
    
    @Override
    public List<User> getAll() throws Exception {
        return broker.loadAll()
                .stream()
                .map(gen -> UserMapper.INSTANCE.fromDto(gen))
                .collect(Collectors.toList());
                
    }
    

    @Override
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        String where = String.format("username = %s and password = %s",
                stringLiteral(username),
                stringLiteral(password));
       
        return UserMapper.INSTANCE.fromDto(broker.loadFirst(where));
    }
    
    
    
}
