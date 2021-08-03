/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.template;

import com.dostojic.climbers.logic.TransactionManager;

/**
 *
 * @author Dejan.Ostojic
 */
public abstract class GeneralUpdateSO<DomainObjectIn, DomainObjectOut>  implements GeneralSO<DomainObjectIn, DomainObjectOut> {

    protected final TransactionManager transactionManager;

    public GeneralUpdateSO(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
            
    public final DomainObjectOut execute(DomainObjectIn domainObject) throws Exception{
        try {
            transactionManager.startTransaction();
            checkPrecondition(domainObject);
            DomainObjectOut result = executeOperation(domainObject);
            transactionManager.commit();
            return result;
        } catch (Exception ex) {
            transactionManager.rollback();
            throw ex;
        }
        
    }

    protected abstract void checkPrecondition(DomainObjectIn domainObject) throws Exception;

    protected abstract DomainObjectOut executeOperation(DomainObjectIn domainObject) throws Exception;
}
