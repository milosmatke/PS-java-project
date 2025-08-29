/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Bibliotekar;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOLogin extends AbstractSO{

    Bibliotekar ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Bibliotekar)){
            throw new Exception("Prosledjeni objekat nije instanca klase Bibliotekar!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        Bibliotekar bibliotekar=(Bibliotekar) ado;
        
        ArrayList<Bibliotekar> bibliotekari = 
                (ArrayList<Bibliotekar>) (ArrayList<?>) DBBroker.getInstance().select(ado); 
       
        for (Bibliotekar b : bibliotekari) {
            if(b.getKorisnickoIme().equals(bibliotekar.getKorisnickoIme()) 
                    && b.getLozinkaHash().equals(bibliotekar.getLozinkaHash())){
                
                ulogovani=b;
                return;
            }
        }
        throw new Exception("Ne postoji uneti bibliotekar");
    }

    public Bibliotekar getUlogovani() {
        return ulogovani;
    }
    
}
