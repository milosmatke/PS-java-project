/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pozajmica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Pozajmica;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOGetAllPozajmica extends AbstractSO{

    ArrayList<Pozajmica> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Pozajmica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Pozajmica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> usluge = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Pozajmica>) (ArrayList<?>) usluge;
    }

    public ArrayList<Pozajmica> getLista() {
        return lista;
    }
    
}
