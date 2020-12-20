/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.logic;

/**
 *
 * @author Dejan.Ostojic
 */
public interface TransactionManager  {

    /**
     * Binds the connection to the current thread.
     * @throws Exception
     */
    void bindConnection() throws Exception;

    /**
     * Starts the transaction. It binds connection to the thread and sets auto commit to false.
     * @throws Exception
     */
    void startTransaction() throws Exception;
    
    /**
     * Commits the transaction. Closes the connection and clears current thread from the connection used.
     * @throws Exception
     */
    void commit() throws Exception;
    /**
     * Rollback the transaction. Closes the connection and clears current thread from the connection used.
     * @throws Exception
     */
    void rollback() throws Exception;
    
    /**
     * Closes the connection and clears current thread from the connection used.
     * @throws Exception
     */
    void releseConnection() throws Exception;
}
