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
import domain.Autor;
import domain.Bibliotekar;
import domain.Clan;
import domain.Knjiga;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

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

    public void addKnjiga(Knjiga knjiga) throws Exception {
       sendRequest(Operations.ADD_KNJIGA, knjiga);
    }

    public void addClan(Clan clan) throws Exception {
        sendRequest(Operations.ADD_CLAN, clan);
    }

    public List<Clan> getAllClan() throws Exception {
         return (List<Clan>) sendRequest(Operations.GET_ALL_CLAN, null);
    }

    public void updateClan(Clan clan) throws Exception {
         sendRequest(Operations.UPDATE_CLAN, clan);
    }

    public void deleteClan(Clan clan) throws Exception {
        sendRequest(Operations.DELETE_CLAN, clan);
    }

    public List<Autor> getAllAutor() throws Exception {
        return (List<Autor>) sendRequest(Operations.GET_ALL_AUTOR,null);
    }


    public List<Knjiga> getAllKnjiga() throws Exception {
       return (List<Knjiga>) sendRequest(Operations.GET_ALL_KNJIGA, null);
    }

    public void updateKnjiga(Knjiga k) throws Exception {
        sendRequest(Operations.UPDATE_KNJIGA, k);
    }
}
