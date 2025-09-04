/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import controller.ServerController;
import domain.Bibliotekar;
import domain.Clan;
import domain.Knjiga;
import java.net.Socket;

/**
 *
 * @author Korisnik
 */
public class HandleClientThread extends Thread{
    private Socket socket;

    
    public HandleClientThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            while(!socket.isClosed()){
                Request request=(Request) new Receiver(socket).receive();
                
                Response response=handleRequest(request);
              
                new Sender(socket).send(response);
                
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
       
        Response response = new Response(ResponseType.SUCCESS, null, null);
        try {
            switch (request.getOperation()) {
                case Operations.LOGIN:
                    Bibliotekar bibliotekar = (Bibliotekar) request.getArgument();
                    Bibliotekar ulogovani = ServerController.getInstance().login(bibliotekar);
                    response.setResult(ulogovani);
                    break;
                case Operations.ADD_CLAN:
                    ServerController.getInstance().addClan((Clan) request.getArgument());
                    break;
                case Operations.GET_ALL_CLAN:
                    response.setResult(ServerController.getInstance().getAllClan());
                    break;
                case Operations.UPDATE_CLAN:
                    ServerController.getInstance().updateClan((Clan) request.getArgument());
                    break;
                    
                case Operations.DELETE_CLAN:
                    ServerController.getInstance().deleteClan((Clan)request.getArgument());
                    break;
                    
                case Operations.GET_ALL_AUTOR:
                    response.setResult(ServerController.getInstance().getAllAutor());
                    break;  
                case Operations.ADD_KNJIGA:
                    ServerController.getInstance().addKnjiga((Knjiga)request.getArgument());
                    break;    
                
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    
    }
    
}
