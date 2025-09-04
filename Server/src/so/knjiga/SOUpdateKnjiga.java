/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Knjiga;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOUpdateKnjiga extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Knjiga)){
            throw new Exception("Prosledjeni objekat nije instanca klase Knjiga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
    
}
