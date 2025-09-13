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
import so.knjiga.SOUpdateKnjiga;

/**
 *
 * @author Korisnik
 */
public class SODeletePozajmica extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Pozajmica)){
            throw new Exception("Prosledjeni objekat nije instanca klase Pozajmica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Pozajmica pozajmica=(Pozajmica) ado;
        StavkaPozajmice sp = new StavkaPozajmice();
        sp.setPozajmica(pozajmica);
        ArrayList<AbstractDomainObject> listaStavki = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select((AbstractDomainObject) sp);
        ArrayList<StavkaPozajmice> lista = (ArrayList<StavkaPozajmice>) (ArrayList<?>) listaStavki;
        System.out.println(listaStavki);
        for (StavkaPozajmice stavka : lista) {
            Knjiga knjiga= stavka.getKnjiga();
            System.out.println(knjiga);
            knjiga.setKolicina(knjiga.getKolicina()+1);
            System.out.println(knjiga.getKolicina());
            try{
            SOUpdateKnjiga so=new SOUpdateKnjiga();
            so.templateExecute(knjiga);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        DBBroker.getInstance().delete(ado);
    }
    
}
