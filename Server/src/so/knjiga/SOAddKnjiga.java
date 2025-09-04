/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Knjiga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOAddKnjiga extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Knjiga)){
            throw new Exception("Prosledjeni objekat nije instanca klase Knjiga");
        }
        
        Knjiga knjiga = (Knjiga) ado;

        ArrayList<Knjiga> knjige = 
                (ArrayList<Knjiga>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        
       // for (Knjiga k : knjige) {
            //if(k.getNaziv().equals(knjiga.getNaziv()) 
                 //   && k.getAutor().getAutorID().equals(k.getAutor().getAutorID())){
               // throw new Exception("Sistem ne moze da kreira novu knjigu!");
           // }
        //
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
