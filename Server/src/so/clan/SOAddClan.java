/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clan;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Clan;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOAddClan extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Clan)){
            throw new Exception("Prosledjeni objekat nije instanca klase Korisik");
        }
        
        Clan clan = (Clan) ado;

        ArrayList<Clan> clanovi = 
                (ArrayList<Clan>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Clan c : clanovi) {
            if(clan.getIme().equals(c.getIme()) && clan.getPrezime().equals(c.getPrezime()) 
                    && clan.getEmail().equals(c.getEmail()) 
                    && clan.getTelefon().equals(c.getTelefon())){
                throw new Exception("Sistem ne moze da kreira clana!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
    
}
