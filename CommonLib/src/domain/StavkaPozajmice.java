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
    private LocalDate datumVracanja; // moze biti null dok je zaduzen
    private String statusStavke;     // 'zaduzen','vracen','izgubljen','ostecen'
    private BigDecimal kazna;

    public StavkaPozajmice() {}

    public StavkaPozajmice(Pozajmica pozajmica, Knjiga knjiga,
                           LocalDate datumVracanja, String statusStavke, BigDecimal kazna) {
        this.pozajmica = pozajmica; this.knjiga = knjiga;
        this.datumVracanja = datumVracanja; this.statusStavke = statusStavke; this.kazna = kazna;
    }
    

    @Override
    public String getTableName() {
        return " stavka_pozajmice ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (pozajmica_id, knjiga_id, datum_vracanja, status_stavke, kazna) ";
    }

    @Override
    public String getInsertValues() {
        return pozajmica.getId() + ", " + knjiga.getId() + ", " +
               (datumVracanja==null? "NULL" : "'" + datumVracanja + "'") + ", " +
               "'" + statusStavke + "', " +
               (kazna==null? "0.00" : kazna.toPlainString());
    }

    @Override
    public String getUpdateValues() {
       return " datum_vracanja=" + (datumVracanja==null? "NULL" : "'" + datumVracanja + "'") +
               ", status_stavke='" + statusStavke + "'" +
               ", kazna=" + (kazna==null? "0.00" : kazna.toPlainString());
    }

    @Override
    public String getPrimaryKeyValue() {
         return " pozajmica_id=" + pozajmica.getId() + " AND knjiga_id=" + knjiga.getId();
    }

    @Override
    public String alias() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN pozajmica p ON p.id = sp.pozajmica_id " +
               " JOIN knjiga k ON pr.id = sp.knjiga_id " ;
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();
        while (rs.next()) {
            Knjiga k = new Knjiga(
                rs.getLong("k.id"),
                rs.getString("k.isbn"),
                rs.getString("k.naslov"),
                rs.getString("k.izdavac"),
                (Integer)(rs.getObject("k.godina_izdanja")==null?null:rs.getInt("k.godina_izdanja")),
                rs.getString("k.zanr"),
                    rs.getBoolean("k.active")
            );
            
            Pozajmica p = new Pozajmica(
                rs.getLong("p.id"),
                null, null,
                rs.getTimestamp("p.datum_izdavanja").toLocalDateTime(),
                rs.getDate("p.rok_vracanja").toLocalDate(),
                rs.getString("p.status"),
                rs.getBigDecimal("p.ukupna_kazna"),
                rs.getString("p.napomena")
            );
            list.add(new StavkaPozajmice(
                p,
                k,
                rs.getDate("sp.datum_vracanja") == null ? null : rs.getDate("sp.datum_vracanja").toLocalDate(),
                rs.getString("sp.status_stavke"),
                rs.getBigDecimal("sp.kazna")
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

    public Knjiga getPrimerak() {
        return knjiga;
    }

    public void setPrimerak(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public LocalDate getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(LocalDate datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public String getStatusStavke() {
        return statusStavke;
    }

    public void setStatusStavke(String statusStavke) {
        this.statusStavke = statusStavke;
    }

    public BigDecimal getKazna() {
        return kazna;
    }

    public void setKazna(BigDecimal kazna) {
        this.kazna = kazna;
    }
    
}
