/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbbrTransactionManager;
import com.dostojic.climbers.logic.TransactionManager;

/**
 *
 * @author Dejan.Ostojic
 */
public class TransactionManagerImpl implements TransactionManager {


    public TransactionManagerImpl() {}


    /**
     * Start transaction. Get connection from data source and bind it to current
     * thread
     *
     * @throws Exception
     */
    @Override
    public void startTransaction() throws Exception {
        DbbrTransactionManager.getCurrent().startTransaction();
    }

    /**
     * Commit transaction and clear from current thread.
     *
     * @throws Exception
     */
    @Override
    public void commit() throws Exception {
        DbbrTransactionManager.getCurrent().commit();
    }

    @Override
    public void rollback() throws Exception {
        DbbrTransactionManager.getCurrent().rollback();
    }

    @Override
    public void bindConnection() throws Exception {
        DbbrTransactionManager.getCurrent().bindConnection();
    }

    @Override
    public void releseConnection() throws Exception {
        DbbrTransactionManager.getCurrent().closeConnAndRemoveFromThread();
    }
    


}
