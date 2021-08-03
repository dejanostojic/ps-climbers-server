/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.swing.bff;

import com.dostojic.climbers.logic.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dejan
 */
public class ServerThread extends Thread {

    private ServerSocket ss;
    private volatile boolean runServer;
    private List<ClientThread> clients;
    private Controller controller;
    
    ServerThread(Controller controller) {
        runServer = true;
        clients = Collections.synchronizedList(new ArrayList<>());
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            
            System.out.println("Server je pokrenut i ceka klijenta");
            while (!isInterrupted() && runServer) {
                
                Socket s = ss.accept();
                System.out.println("Server je prihvatio klijenta!");
                ClientThread clientThread = new ClientThread(s,clients, controller);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(FServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("stop");
        try {
            if (ss != null && !ss.isClosed()) {
                ss.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setRunServer(boolean runServer) {
        this.runServer = runServer;
    }

    public void reRun() {
        runServer = true;
        this.start();
//        run();
    }

    public ServerSocket getSs() {
        return ss;
    }

    public void stopEntireServer() {
        synchronized (this) {
            runServer = false;
            this.interrupt();
            try {
                this.getSs().close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            clients.parallelStream().forEach((ct) -> {
                ct.stopThread();
            });
        }
        
        
    }
}
