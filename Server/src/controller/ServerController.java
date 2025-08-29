/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Bibliotekar;
import so.login.SOLogin;

/**
 *
 * @author Korisnik
 */
public class ServerController {
    private static ServerController instance;
    private static Bibliotekar ulogovani;
    
    public static ServerController getInstance() {
        if(instance==null){
            instance=new ServerController();
        }
        return instance;
    }

    public Bibliotekar login(Bibliotekar bibliotekar) throws Exception {
        SOLogin so=new SOLogin();
        
        so.templateExecute(bibliotekar);
        
        ulogovani=so.getUlogovani();
        return so.getUlogovani();
    }
}
