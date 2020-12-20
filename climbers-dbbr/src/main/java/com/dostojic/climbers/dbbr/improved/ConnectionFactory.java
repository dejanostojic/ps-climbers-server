/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.dbbr.improved;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deprecated class 
 * @author Dejan.Ostojic
 */
@Deprecated
public class ConnectionFactory {
/*
    This will be deleted i guess. Should use Transaction
    
    private static volatile ConnectionFactory instance;
    private HikariDataSource ds;
    
    private ConnectionFactory() {
        String propsFile = System.getProperty("hikariConfig", "src/test/resources/hikariConfig.properties");
        HikariConfig config = new HikariConfig(propsFile);
        ds = new HikariDataSource(config);
    }

    public static ConnectionFactory getInstance2() {
        if (instance == null) {
            synchronized (ConnectionFactory.class) {
                if (instance == null) {
                    instance = new ConnectionFactory();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            return ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
    }

    Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
}
