/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.autor;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Autor;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOGetAllAutor extends AbstractSO{
     private ArrayList<Autor> lista;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Autor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Autor!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> autori = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Autor>) (ArrayList<?>) autori;
    }

    public ArrayList<Autor> getLista() {
        return lista;
    }
}
