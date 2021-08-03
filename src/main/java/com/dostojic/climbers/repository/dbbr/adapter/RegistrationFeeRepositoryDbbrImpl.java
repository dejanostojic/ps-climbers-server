/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbBroker;
import com.dostojic.climbers.domain.RegistrationFee;
import com.dostojic.climbers.repository.RegistrationFeeRepository;
import java.util.List;

/**
 *
 * @author Dejan.Ostojic
 */
public class RegistrationFeeRepositoryDbbrImpl implements RegistrationFeeRepository{
    private final DbBroker<RegistrationFeeDto, RegistrationFeeCompositeId> registrationFeeDbbr;

    public RegistrationFeeRepositoryDbbrImpl() {
        this.registrationFeeDbbr = new DbBroker<RegistrationFeeDto, RegistrationFeeCompositeId> (RegistrationFeeDto.class){};
    }

    @Override
    public RegistrationFee insert(RegistrationFee registrationFee) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RegistrationFee update(RegistrationFee registrationFee) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(RegistrationFee registrationFee) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RegistrationFee> findByCompetitionId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
