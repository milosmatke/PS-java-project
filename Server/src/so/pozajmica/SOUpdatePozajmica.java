/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pozajmica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Knjiga;
import domain.Pozajmica;
import domain.StavkaPozajmice;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOUpdatePozajmica extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Pozajmica)){
            throw new Exception("Prosledjeni objekat nije instanca klase Pozajmica!");
        }
        
        Pozajmica pozajmica=(Pozajmica) ado;
        
        if (pozajmica.getDatumIzdavanja().after(pozajmica.getRokVracanja())) {
            throw new Exception("Datum izdavanja ne sme biti nakon roka za vracanje");
        }

        if (pozajmica.getListaStavki() == null||pozajmica.getListaStavki().isEmpty()) {
            throw new Exception("Pozajmica mora da ima bar jednu stavku!");
        }
        if(!pozajmica.getClan().getStatus().equals("aktivan")){
            throw new Exception("Clan mora imati aktivan status.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {


        Pozajmica novaPozajmica = (Pozajmica) ado;

    
        StavkaPozajmice spZaSelect = new StavkaPozajmice();
        spZaSelect.setPozajmica(novaPozajmica);
        //@SuppressWarnings("unchecked")
        ArrayList<StavkaPozajmice> stareStavke =
        (ArrayList<StavkaPozajmice>)(ArrayList<?>) DBBroker.getInstance().select(spZaSelect);

    
        for (StavkaPozajmice staraStavka : stareStavke) {
            if (!novaPozajmica.getListaStavki().contains(staraStavka)) {
                Knjiga k = staraStavka.getKnjiga();
                k.setKolicina(k.getKolicina()+1);
                DBBroker.getInstance().update(k);
            }
        }

    
        for (StavkaPozajmice novaStavka : novaPozajmica.getListaStavki()) {
            if (!stareStavke.contains(novaStavka)) {
                Knjiga k = novaStavka.getKnjiga();
                k.setKolicina(k.getKolicina());
                DBBroker.getInstance().update(k);
            }
        }

   
        DBBroker.getInstance().update(novaPozajmica);

    
        for (StavkaPozajmice staraStavka : stareStavke) {
            DBBroker.getInstance().delete(staraStavka);
        }  

    
        for (StavkaPozajmice novaStavka : novaPozajmica.getListaStavki()) {
            novaStavka.setPozajmica(novaPozajmica);
            DBBroker.getInstance().insert(novaStavka);
        }
    }
}
