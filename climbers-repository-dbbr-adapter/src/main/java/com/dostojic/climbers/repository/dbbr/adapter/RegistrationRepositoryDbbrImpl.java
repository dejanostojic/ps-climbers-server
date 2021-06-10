/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbBroker;
import com.dostojic.climbers.domain.Registration;
import com.dostojic.climbers.repository.RegistrationRepository;
import com.dostojic.climbers.repository.dbbr.adapter.mapper.CompetitionMapper;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public class RegistrationRepositoryDbbrImpl implements RegistrationRepository {

    private final DbBroker<RegistrationDto, RegistrationCompositeId> registrationDbbr;

    public RegistrationRepositoryDbbrImpl() {
        this.registrationDbbr = new DbBroker<RegistrationDto, RegistrationCompositeId>(RegistrationDto.class) {
        };
    }

    @Override
    public Registration insert(Registration registration) throws Exception {
        RegistrationDto registrationDto = CompetitionMapper.INSTANCE.toDto(registration);
        System.out.println("Registration dto to insert: " + registrationDto);
        RegistrationDto inserted = registrationDbbr.insert(registrationDto);

        return CompetitionMapper.INSTANCE.fromDto(inserted);
    }

    @Override
    public Registration update(Registration registration) throws Exception {
        RegistrationDto registrationDto = CompetitionMapper.INSTANCE.toDto(registration);
        System.out.println("REGISTRATION DTO update: " + registrationDto);
        boolean updated = registrationDbbr.update(registrationDto);
        if (!updated) {
            throw new Exception("Registration can not be updated! " + registrationDto);
        }
        return registration;
    }

    @Override
    public void delete(Registration registration) throws Exception {
        RegistrationDto registrationDto = CompetitionMapper.INSTANCE.toDto(registration);
        System.out.println("Resgistration DTO: " + registrationDto);
        boolean deleted = registrationDbbr.delete(registrationDto);
        if (!deleted){
            throw new Exception("Resgistration can not be deleted! " + registrationDto);
        }
    }

    @Override
    public List<Registration> findByCompetitionId(Integer id) throws Exception {
        return CompetitionMapper.INSTANCE.toRegistrations(registrationDbbr.loadList("competition_id = " + id, null));
    }

}
