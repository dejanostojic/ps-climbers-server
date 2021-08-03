package com.dostojic.climbers.dbbr.improved;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author dejan
 */
@FunctionalInterface
public interface ResultSetProcessor {
    public void process(ResultSet resultSet) throws Exception;
}
