/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pozajmica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Pozajmica;
import domain.StavkaPozajmice;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOGetPozajmica extends AbstractSO{

    Pozajmica pozajmica;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Pozajmica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Pozajmica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        pozajmica= (Pozajmica) ado;
        System.out.println(pozajmica);
        StavkaPozajmice sp = new StavkaPozajmice();
        sp.setPozajmica(pozajmica);
        ArrayList<AbstractDomainObject> konacnaLista=(ArrayList<AbstractDomainObject>) DBBroker.getInstance().select((AbstractDomainObject) sp);
         
        ArrayList<StavkaPozajmice> lista = (ArrayList<StavkaPozajmice>) (ArrayList<?>) konacnaLista;
        pozajmica.setListaStavki(lista);
    }

    public Pozajmica getPozajmica() {
        return pozajmica;
    }
    
}
