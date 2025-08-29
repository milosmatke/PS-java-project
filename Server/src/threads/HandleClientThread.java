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
