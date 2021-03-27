/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.climbers.swing.bff;

import com.dostojic.climbers.common.communication.Receiver;
import com.dostojic.climbers.common.communication.Request;
import com.dostojic.climbers.common.communication.Response;
import com.dostojic.climbers.common.communication.Sender;
import com.dostojic.climbers.common.dto.ClimberDto;
import com.dostojic.climbers.common.dto.CompetitionDto;
import com.dostojic.climbers.common.dto.CompetitionSearchCriteriaDto;
import com.dostojic.climbers.common.dto.LoginCredentialsDto;
import com.dostojic.climbers.domain.Climber;
import com.dostojic.climbers.domain.Competition;
import com.dostojic.climbers.domain.User;
import com.dostojic.climbers.logic.Controller;
import com.dostojic.climbers.swing.bff.mapper.ClimberMapper;
import com.dostojic.climbers.swing.bff.mapper.CompetitionMapper;
import com.dostojic.climbers.swing.bff.mapper.LoginCredentialsMapper;
import com.dostojic.climbers.swing.bff.mapper.UserMapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author dejan
 */
public class ClientThread extends Thread {

    private Socket s;
    private List<ClientThread> clients;
    private ObjectInputStream inSoket;
    private ObjectOutputStream outSoket;
    private User user;
    Controller controller;

    public ClientThread(Socket s, List<ClientThread> clients, Controller controller) {
        this.s = s;
        this.clients = clients;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {

            handleClient(s, controller);

        } catch (Exception ex) {
            System.out.println("removing from client list");
            clients.remove(this);
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void handleClient(Socket socket, Controller controller) throws Exception {
        Sender sender = new Sender(socket);
        Receiver receiver = new Receiver(socket);
        while (true) {
            Request request = (Request) receiver.receive();
            Response response = new Response();
            try {
                switch (request.getOperation()) {
                    case LOGIN:
                        LoginCredentialsDto loginCredentials = (LoginCredentialsDto) request.getArgument();
                        // TODO: Validate argument before core logic call!
                        // Throw appropriate exception if validation fails
                        // e.g. request mandatory fields to exist
                        response.setResult(UserMapper.INSTANCE.toDto(
                                controller.login(
                                        LoginCredentialsMapper.INSTANCE.fromDto(loginCredentials))));
                        break;
                    case GET_ALL_CLIMBERS:
                        response.setResult(ClimberMapper.INSTANCE.toDto(
                                controller.getAllClimbers()));
                        break;
                    case FIND_CLIMBER:
                        Integer id = (Integer) request.getArgument();
                        Climber climber = controller.findClimberById(id);
                        if (climber != null) {
                            response.setResult(ClimberMapper.INSTANCE.toDto(climber));
                        } else {
                            // we throw exception because it is expected to get climber back
                            // TODO Throw Bussines exception not general one!
                            // QUESTION: Would it be better for controler to throw this exception?!
                            response.setException(new Exception("Requested climber does not exist."));
                        }
                        break;
                    case DELETE_CLIMBER:
                        Integer deleteId = (Integer) request.getArgument();
                        controller.deleteClimberById(deleteId);
                        break;
                    case UPDATE_CLIMBER:
                        ClimberDto climberRequestObject = (ClimberDto) request.getArgument();
                        controller.updateClimber(ClimberMapper.INSTANCE.fromDto(climberRequestObject));
                        break;
                    case SAVE_CLIMBER:
                        ClimberDto climberCreate = (ClimberDto) request.getArgument();
                        controller.createClimber(ClimberMapper.INSTANCE.fromDto(climberCreate));
                        break;
                    case SAVE_COMPETITION:
                        CompetitionDto competitionCreate = (CompetitionDto) request.getArgument();
                        controller.saveCompetition(CompetitionMapper.INSTANCE.fromDto(competitionCreate));
                        break;
                    case SEARCH_COMPETITIONS:
                        CompetitionSearchCriteriaDto competitionSearchCriteriaDto = (CompetitionSearchCriteriaDto) request.getArgument();
                        response.setResult(
                                controller.searchCompetitions(CompetitionMapper.INSTANCE.toSearchCriteria(competitionSearchCriteriaDto))
                                        .stream()
                                        .map(CompetitionMapper.INSTANCE::toDto)
                                        .collect(toList())
                        );
                        break;
                    case FIND_COMPETITION:
                        Integer compId = (Integer) request.getArgument();
                        Competition competition = controller.findCompetitionById(compId);
                        if (competition != null) {
                            response.setResult(CompetitionMapper.INSTANCE.toDto(competition));
                        } else {
                            // we throw exception because it is expected to get climber back
                            // TODO Throw Bussines exception not general one!
                            // QUESTION: Would it be better for controler to throw this exception?!
                            response.setException(new Exception("Requested competition does not exist."));
                        }
                        break;
                    case UPDATE_COMPETITION:
                        CompetitionDto competitionUpdate = (CompetitionDto) request.getArgument();
                        controller.updateCompetition(CompetitionMapper.INSTANCE.fromDto(competitionUpdate));
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setException(e);
            }
            try {
                sender.send(response);
            } catch (Exception e) {
                System.out.println("Error in sending response to client");
                e.printStackTrace();
            }
        }
    }

    void stopThread() {
        synchronized (this) {
            this.interrupt();
            try {
                this.s.close();
            } catch (IOException ex) {
            }
        }
    }

    private void informStop() {

    }

    public ObjectOutputStream getOutSoket() {
        return outSoket;
    }

    public void setOutSoket(ObjectOutputStream outSoket) {
        this.outSoket = outSoket;
    }

    public Socket getS() {
        return s;
    }

    public User getUser() {
        return user;
    }

}
