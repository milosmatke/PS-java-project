/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import domain.Bibliotekar;
import domain.Knjiga;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Korisnik
 */
public class ClientController {
    
    private static ClientController instance;

    public ClientController() {
    }
    
    public static ClientController getInstance() {
        if(instance==null){
            instance=new ClientController();
        }
        return instance;
    }

    public Bibliotekar login(Bibliotekar bibliotekar) throws Exception {
        return (Bibliotekar) sendRequest(Operations.LOGIN, bibliotekar);
    }
    
    
    
    
    
    private Object sendRequest(int operation, Object data) throws Exception {
        Request request=new Request(operation, data);
        
        ObjectOutputStream out= new ObjectOutputStream(Communication.getInstance().getSocket().getOutputStream());
        out.writeObject(request);
        
        ObjectInputStream in= new ObjectInputStream(Communication.getInstance().getSocket().getInputStream());
        Response response= (Response) in.readObject();
        
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }else{
            return response.getResult();
        }
        
    }

    public void addKnjiga(Knjiga knjiga) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
