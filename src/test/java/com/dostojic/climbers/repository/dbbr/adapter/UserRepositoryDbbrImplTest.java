/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.repository.dbbr.adapter;

import com.dostojic.climbers.dbbr.improved.DbbrTransactionManager;
import com.dostojic.climbers.domain.User;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Dejan.Ostojic
 */
public class UserRepositoryDbbrImplTest {
    private static TransactionManagerImpl transactionManagerImpl;
    private static UserRepositoryDbbrImpl userRepositoryDbbrImpl;
    
    @Test
    public void whenGetAllExecutedResutlsAreFine() throws Exception {
            List<User> all = userRepositoryDbbrImpl.getAll();
            Assertions.assertEquals(2, all.size());
    }
    
    @Test
    public void whenInNotInTransactionFail() throws Exception {
        transactionManagerImpl.rollback();
        
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
                List<User> loadAll = userRepositoryDbbrImpl.getAll();
          }, "It is required to throw exception when trying to run queries wihout context");
        
        String expectedMessage = "Not in persistance context";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage), String.format("The exception message must contain: %s ", expectedMessage) );
    }

    
    
    @BeforeAll
    private static void beforeAll(){
//        System.setProperty("hikariConfig", new File("src/test/resources/hikariConfig.properties").getAbsolutePath()); 

        transactionManagerImpl = new TransactionManagerImpl();
        userRepositoryDbbrImpl = new UserRepositoryDbbrImpl();
    }
    
    @BeforeEach
    private void beforeEach() throws SQLException, Exception {
        transactionManagerImpl.startTransaction();
        try (Statement s = DbbrTransactionManager.getCurrent().createStatement();) {
            
            s.executeUpdate("insert into user (id,first_name,last_name,username,password) values(1,'fname', 'lastName','u','u')");
            s.executeUpdate("insert into user (id,first_name,last_name,username,password) values(2,'fname2', 'lastName2','u2','u2')");
            ResultSet rs = s.executeQuery("select * from user");
            
            while (rs.next()){
                System.out.println("user: ");
                System.out.println("id: " + rs.getLong("id"));
                System.out.println("first_name: " + rs.getString("first_name"));
            }

        }
    }
    
    
    @AfterEach
    private void afterEach() {
        try{
            transactionManagerImpl.rollback();
        }catch(Exception e){
        }
    }
    

/*

    @Override
    protected DataSource getDataSource() {
        return TransactionManagerImpl.DS;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("UserGeneralDaoTest.xml"));    }
    
    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }
    */
}
