/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class StavkaPozajmice implements AbstractDomainObject{
    
    private Pozajmica pozajmica; // PK deo 1
    private Knjiga knjiga;   // PK deo 2
    private int rbStavke;
   

    public StavkaPozajmice() {}

    public StavkaPozajmice(Pozajmica pozajmica, Knjiga knjiga,
                           int rbStavke) {
        this.pozajmica = pozajmica; this.knjiga = knjiga;
        this.rbStavke=rbStavke;
    }
    

    @Override
    public String getTableName() {
        return " stavka_pozajmice ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (pozajmica_id, knjiga_id, rbStavke) ";
    }

    @Override
    public String getInsertValues() {
        return pozajmica.getId() + ", " + knjiga.getId() + ", " +rbStavke;
               
    }

    @Override
    public String getUpdateValues() {
       return "";
    }

    @Override
    public String getPrimaryKeyValue() {
         return " pozajmica_id = " + pozajmica.getId();
    }

    @Override
    public String alias() {
        return " sp ";
    }

    @Override
    public String join() {
        return "INNER JOIN pozajmica p ON (p.id = sp.pozajmica_id) "
                +"INNER JOIN clan c ON (c.id = p.clan_id)"
                +"INNER JOIN Bibliotekar b ON (b.id = p.bibliotekar_id)"
		+"INNER JOIN Knjiga k ON (k.id = sp.knjiga_id) "
                +"INNER JOIN Autor a ON (k.autor_id=a.id)";
    }

    @Override
    public String condition() {
        return "WHERE p.id= "+pozajmica.getId()+" ORDER BY k.naslov";
    }
    

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();
        while (rs.next()) {
            Autor autor = new Autor(rs.getLong("id"),
                    rs.getString("ime"), rs.getString("prezime"));
            Knjiga k= new Knjiga(rs.getLong("id"),  rs.getString("naslov"),  rs.getString("izdavac")
                    ,rs.getInt("godinaIzdanja") , rs.getString("zanr"), autor, rs.getInt("kolicina"));

            
            
            Pozajmica p = new Pozajmica(
                rs.getLong("p.id"),
                null, null,
                rs.getDate("p.datum_izdavanja"),
                rs.getDate("p.rok_vracanja"),null
                
            );
            list.add(new StavkaPozajmice(
                p,
                k,
                rs.getInt("rbStavke")
            ));
        }
        rs.close(); return list;
    }

    public Pozajmica getPozajmica() {
        return pozajmica;
    }

    public void setPozajmica(Pozajmica pozajmica) {
        this.pozajmica = pozajmica;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    
    
    
}
