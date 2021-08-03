/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.boot;

import com.dostojic.climbers.swing.bff.FServer;
import javax.swing.SwingUtilities;

/**
 *
 * @author planina
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->
            new FServer(MainFactory.getController()).setVisible(true)
        );
    }
}
