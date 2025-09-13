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
        if (!(ado instanceof Pozajmica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Pozajmica.");
        }

        Pozajmica pozajmica = (Pozajmica) ado;

        if (pozajmica.getDatumIzdavanja().after(pozajmica.getRokVracanja())) {
            throw new Exception("Datum izdavanja ne sme biti nakon datuma isteka.");
        }

        if (pozajmica.getListaStavki() == null || pozajmica.getListaStavki().isEmpty()) {
            throw new Exception("Pozajmica mora da ima bar jednu stavku.");
        }

        // Validacija dostupnosti knjiga iz stavki koje korisnik unosi
        for (StavkaPozajmice stavka : pozajmica.getListaStavki()) {
            if (stavka.getKnjiga().getKolicina() <= 0) {
                throw new Exception("Knjiga '" + stavka.getKnjiga().getNaslov() + "' nije dostupna za pozajmicu.");
            }
        }

        //  Provera da li već postoji identična pozajmica
        ArrayList<Pozajmica> pozajmice =
                (ArrayList<Pozajmica>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Pozajmica p : pozajmice) {
            if (pozajmica.getDatumIzdavanja().equals(p.getDatumIzdavanja())
                    && pozajmica.getRokVracanja().equals(p.getRokVracanja())
                    && pozajmica.getClan().getId() == p.getClan().getId()) {
                throw new Exception("Slična pozajmica već postoji za ovog člana u istom periodu.");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        //  Unos pozajmice i dobijanje ID-a
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long pozajmicaID = tableKeys.getLong(1);

        Pozajmica pozajmica = (Pozajmica) ado;
        pozajmica.setId(pozajmicaID);

        //  Unos stavki i smanjenje količine knjige
        for (StavkaPozajmice stavka : pozajmica.getListaStavki()) {
            stavka.setPozajmica(pozajmica); // dodeli generisani ID pozajmice
            DBBroker.getInstance().insert(stavka);

            // Smanji količinu knjige
            Knjiga knjiga = stavka.getKnjiga();
            knjiga.setKolicina(knjiga.getKolicina() - 1);
            DBBroker.getInstance().update(knjiga);
        }
    }
}
