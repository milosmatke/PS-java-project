/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pozajmica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Pozajmica;
import domain.StavkaPozajmice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOAddPozajmica extends AbstractSO{
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Pozajmica)){
            throw new Exception("Prosledjeni objekat nije instanca klase Pozajmica");
        }
        
        Pozajmica pozajmica = (Pozajmica) ado;
        
        if (pozajmica.getDatumIzdavanja().after(pozajmica.getRokVracanja())) {
            throw new Exception("Datum izdavanja ne sme biti nakon datuma isteka");
        }

        if (pozajmica.getListaStavki().isEmpty()) {
            throw new Exception("Pozajmica mora da ima bar jednu stavku!");
        }
        
        

        ArrayList<Pozajmica> pozajmice = 
                (ArrayList<Pozajmica>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        StavkaPozajmice sp = new StavkaPozajmice();
       sp.setPozajmica(pozajmica);
       ArrayList<AbstractDomainObject> listaStavki=(ArrayList<AbstractDomainObject>) DBBroker.getInstance().select((AbstractDomainObject) sp);
       ArrayList<StavkaPozajmice> lista = (ArrayList<StavkaPozajmice>) (ArrayList<?>) listaStavki;
            for (StavkaPozajmice stavka : lista) {
                if(stavka.getKnjiga().getKolicina()<=0){
                    throw new Exception("Sistem ne moze da kreira pozajmicu!");
                }
            }
        
        for (Pozajmica p : pozajmice) {
            if(pozajmica.getDatumIzdavanja().equals(p.getDatumIzdavanja()) 
                    && pozajmica.getRokVracanja().equals(p.getRokVracanja()) 
                    && pozajmica.getClan().getId()==(p.getClan().getId())){
                throw new Exception("Sistem ne moze da kreira pozajmicu!");
            }
            
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long pozajmicaID = tableKeys.getLong(1);

        Pozajmica p = (Pozajmica) ado;
        p.setId(pozajmicaID);

        for (StavkaPozajmice stavka : p.getListaStavki()) {
            stavka.setPozajmica(p);
            DBBroker.getInstance().insert(stavka);
        }
        
    }
}
