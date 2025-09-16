/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Autor;
import domain.Bibliotekar;
import domain.Clan;
import domain.Knjiga;
import domain.Pozajmica;
import so.autor.SOGetAllAutor;
import so.clan.SOAddClan;
import so.clan.SODeleteClan;
import so.clan.SOGetAllClan;
import so.clan.SOUpdateClan;
import so.knjiga.SOAddKnjiga;
import so.knjiga.SOGetAllKnjiga;
import so.knjiga.SOUpdateKnjiga;
import so.login.SOLogin;
import so.pozajmica.SOAddPozajmica;
import so.pozajmica.SODeletePozajmica;
import so.pozajmica.SOGetAllPozajmica;
import so.pozajmica.SOGetPozajmica;
import so.pozajmica.SOUpdatePozajmica;

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

    public Object getAllKnjiga() throws Exception {
        SOGetAllKnjiga so= new SOGetAllKnjiga();
        so.templateExecute(new Knjiga());
        return so.getLista();
    }

    public void updateKnjiga(Knjiga knjiga) throws Exception {
        SOUpdateKnjiga so=new SOUpdateKnjiga();
        so.templateExecute(knjiga);
    }

    public void addPozajmica(Pozajmica pozajmica) throws Exception {
        SOAddPozajmica so=new SOAddPozajmica();
        so.templateExecute(pozajmica);
    }

    public Object getAllPozajmica() throws Exception {
        SOGetAllPozajmica so=new SOGetAllPozajmica();
        so.templateExecute(new Pozajmica());
        return so.getLista();
    }
    
    public void deletePozajmica(Pozajmica pozajmica) throws Exception {
        SODeletePozajmica so=new SODeletePozajmica();
        so.templateExecute(pozajmica);
    }
    
    public void updatePozajmica(Pozajmica pozajmica) throws Exception {
        SOUpdatePozajmica so= new SOUpdatePozajmica();
         so.templateExecute(pozajmica);
    }
    
    public Object getPozajmica(Pozajmica pozajmica) throws Exception {
        SOGetPozajmica so=new SOGetPozajmica();
        so.templateExecute(pozajmica);
        return so.getPozajmica();
    }

    
}
