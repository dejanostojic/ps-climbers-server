/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.dbbr.improved;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dejan
 */
public class QueryUtils {

    public QueryUtils() {
    }

    public static String stringLiteral(String value) {
        if (value == null) {
            return "NULL";
        }
        return mySqlStringLiteral(value);
    }

    private static String mySqlStringLiteral(String value) {
        StringBuilder sql = new StringBuilder();
        sql.append('\'');
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '\0':
                    sql.append("\\0");
                    break;
                case '\n':
                    sql.append("\\n");
                    break;
                case '\r':
                    sql.append("\\r");
                    break;
                case '\\':
                    sql.append("\\\\");
                    break;
                case '\"':
                    sql.append("\\\"");
                    break;
                case '\'':
                    sql.append("\\\'");
                    break;
                case (char) 26:
                    sql.append("\\z");
                    break;
                default:
                    sql.append(c);
            }
        }
        sql.append('\'');
        return sql.toString();
    }

    public static String mySqlLikeLiteral(String value) {
        return mySqlStringLiteral("%" + value + "%");
    }

    public static String mySqlEscapeLiteral(String value) {
        String ESCAPE_LITERAL = "`";
        return ESCAPE_LITERAL + value + ESCAPE_LITERAL;
    }

    public static void forEach(String sql, ResultSetProcessor closure) throws Exception {
        try (Statement s = DbbrTransactionManager.getCurrent().createStatement();
                ResultSet r = s.executeQuery(sql)) {
            closure.process(r);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int exec(String sql) throws Exception {
        try (Statement s = DbbrTransactionManager.getCurrent().createStatement();) {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
