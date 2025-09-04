/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Autor;
import domain.Bibliotekar;
import domain.Clan;
import domain.Knjiga;
import so.autor.SOGetAllAutor;
import so.clan.SOAddClan;
import so.clan.SODeleteClan;
import so.clan.SOGetAllClan;
import so.clan.SOUpdateClan;
import so.knjiga.SOAddKnjiga;
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


    public void addClan(Clan clan) throws Exception {
       
        SOAddClan so=new SOAddClan();
        so.templateExecute(clan);
    
    }

    public Object getAllClan() throws Exception {
        SOGetAllClan so= new SOGetAllClan();
        so.templateExecute(new Clan());
        return so.getLista();
    }

    public void updateClan(Clan clan) throws Exception {
        SOUpdateClan so=new SOUpdateClan();
        so.templateExecute(clan);
    }

    public void deleteClan(Clan clan) throws Exception {
        SODeleteClan so=new SODeleteClan();
        so.templateExecute(clan);
        
    }

    public Object getAllAutor() throws Exception {
        SOGetAllAutor so= new SOGetAllAutor();
        so.templateExecute(new Autor());
        return so.getLista();
    }

    public void addKnjiga(Knjiga knjiga) throws Exception {
        SOAddKnjiga so= new SOAddKnjiga();
        so.templateExecute(knjiga);
    }
}
