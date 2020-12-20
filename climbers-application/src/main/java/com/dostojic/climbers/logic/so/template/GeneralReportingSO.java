/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic.so.template;

import com.dostojic.climbers.logic.so.template.GeneralSO;
import com.dostojic.climbers.logic.TransactionManager;

/**
 *
 * @author Dejan.Ostojic
 * @param <InputParam>
 * @param <OperationResult>
 */
public abstract class GeneralReportingSO<InputParam, OperationResult>  implements GeneralSO {

    protected final TransactionManager transactionManager;

    public GeneralReportingSO(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
            
    public final OperationResult execute(InputParam domainObject) throws Exception{
        try {
            transactionManager.bindConnection();
            return executeOperation(domainObject);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            transactionManager.releseConnection();
        }
    }


    protected abstract OperationResult executeOperation(InputParam domainObject) throws Exception;
}
