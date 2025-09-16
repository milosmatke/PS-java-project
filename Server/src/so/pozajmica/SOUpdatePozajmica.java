/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pozajmica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Pozajmica;
import domain.StavkaPozajmice;
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
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
        Pozajmica pozajmica=(Pozajmica) ado;
        System.out.println(pozajmica);
        System.out.println(pozajmica.getListaStavki());
        
        DBBroker.getInstance().delete(pozajmica.getListaStavki().get(0));
        
        
        for (StavkaPozajmice sp : pozajmica.getListaStavki()) {
            sp.setPozajmica(pozajmica);
            DBBroker.getInstance().insert(sp);
            
        }
    }
    
}
