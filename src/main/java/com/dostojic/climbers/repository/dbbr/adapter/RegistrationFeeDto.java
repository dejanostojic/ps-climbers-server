/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.annotation.Column;
import com.dostojic.climbers.dbbr.improved.annotation.CompositeId;
import com.dostojic.climbers.dbbr.improved.annotation.Table;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Dejan.Ostojic
 */
@Table(name = "registration_fee", autoIncrement = false)
public class RegistrationFeeDto {
    
    @CompositeId
    private RegistrationFeeCompositeId id;
    private CompetitionDto competition;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "end_date")
    private Date endDate;

    public RegistrationFeeDto() {
    }

    public RegistrationFeeCompositeId getId() {
        return id;
    }

    public void setId(RegistrationFeeCompositeId id) {
        this.id = id;
    }

    public CompetitionDto getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDto competition) {
        this.competition = competition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    

}
