/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.server;

import static com.dostojic.climbers.communication.Operation.LOGIN;
import com.dostojic.climbers.communication.Receiver;
import com.dostojic.climbers.communication.Request;
import com.dostojic.climbers.communication.Response;
import com.dostojic.climbers.communication.Sender;
import com.dostojic.climbers.controller.Controller;
import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.domain.User;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

/**
 *
 * @author planina
 */
public class Server {

    volatile Controller controller;

    public Server(Controller controller) {
        this.controller = controller;
    }
    
    
    
    public void startServer() {
        try {
            ServerSocket serverSocket=new ServerSocket(9000);
            System.out.println("Waiting for connection...");
            Socket socket=serverSocket.accept();
            System.out.println("Connected!");
            handleClient(socket, controller);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleClient(Socket socket, Controller controller) throws Exception {
        Sender sender=new Sender(socket);
        Receiver receiver=new Receiver(socket);
        while(true){
            Request request=(Request)receiver.receive();
            Response response=new Response();
            try{
            switch(request.getOperation()){
                case LOGIN:
                    User user=(User)request.getArgument();
                    // TODO: Validate argument before core logic call!
                    // Throw appropriate exception if validation fails
                    // e.g. request mandatory fields to exist
                    response.setResult(controller.login(user.getUsername(), user.getPassword()));
                    break;
                case GET_ALL_CLIMBERS:
                    response.setResult(controller.getAllClimbers());
                    break;
                case FIND_CLIMBER:
                    Integer id=(Integer)request.getArgument();
                    Optional<Climber> optionalClimber = controller.findClimberById(id);
                    if (optionalClimber.isPresent()){
                        response.setResult(optionalClimber.get());
                    }else{
                        // we throw exception because it is expected to get climber back
                        // TODO Throw Bussines exception not general one!
                        // QUESTION: Would it be better for controler to throw this exception?!
                        response.setException(new Exception("Requested climber does not exist."));
                    }
                    break;
                case DELETE_CLIMBER:
                    Integer deleteId=(Integer)request.getArgument();
                    controller.deleteClimberById(deleteId);
                    break;

            }
            }catch(Exception e){
                e.printStackTrace();
                response.setException(e);
            }
            sender.send(response);
        }
    }
    
}
