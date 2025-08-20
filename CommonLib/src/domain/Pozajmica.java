/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class Pozajmica implements AbstractDomainObject{
    private long id;
    private Clan clan;
    private Bibliotekar bibliotekar;
    private LocalDateTime datumIzdavanja;
    private LocalDate rokVracanja;
    private String status;          // 'aktivna','zatvorena','otkazana'
    private BigDecimal ukupnaKazna; // DECIMAL(10,2)
    private String napomena;

    public Pozajmica() {}

    public Pozajmica(long id, Clan clan, Bibliotekar bibliotekar,
                     LocalDateTime datumIzdavanja, LocalDate rokVracanja,
                     String status, BigDecimal ukupnaKazna, String napomena) {
        this.id=id; this.clan=clan; this.bibliotekar=bibliotekar;
        this.datumIzdavanja=datumIzdavanja; this.rokVracanja=rokVracanja;
        this.status=status; this.ukupnaKazna=ukupnaKazna; this.napomena=napomena;
    }
    

    @Override
    public String getTableName() {
        return " pozajmica ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (clan_id, bibliotekar_id, datum_izdavanja, rok_vracanja, status, ukupna_kazna, napomena) ";
    }

    @Override
    public String getInsertValues() {
        return clan.getId() + ", " + bibliotekar.getId() + ", " +
               "'" + datumIzdavanja + "', '" + rokVracanja + "', " +
               "'" + status + "', " + (ukupnaKazna==null? "0.00" : ukupnaKazna.toPlainString()) + ", " +
               (napomena==null? "NULL" : "'" + napomena + "'");
    }

    @Override
    public String getUpdateValues() {
        return " clan_id=" + clan.getId() +
               ", bibliotekar_id=" + bibliotekar.getId() +
               ", datum_izdavanja='" + datumIzdavanja + "'" +
               ", rok_vracanja='" + rokVracanja + "'" +
               ", status='" + status + "'" +
               ", ukupna_kazna=" + (ukupnaKazna==null? "0.00" : ukupnaKazna.toPlainString()) +
               ", napomena=" + (napomena==null? "NULL" : "'" + napomena + "'");
    }

    @Override
    public String getPrimaryKeyValue() {
        return " id=" + id;
    }

    @Override
    public String alias() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN clan c ON c.id = p.clan_id " +
               " JOIN bibliotekar b ON b.id = p.bibliotekar_id ";
        // Stavke i primerci se učitavaju posebnim upitom preko klase StavkaPozajmice
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();
        while (rs.next()) {
            Clan c = new Clan(
                rs.getLong("c.id"),
                rs.getString("c.broj_clanske"),
                rs.getString("c.ime"),
                rs.getString("c.prezime"),
                rs.getString("c.email"),
                rs.getString("c.telefon"),
                rs.getDate("c.datum_isteka").toLocalDate(),
                rs.getString("c.status"),
                rs.getTimestamp("c.created_at").toLocalDateTime()
            );
            Bibliotekar b = new Bibliotekar(
                rs.getLong("b.id"),
                rs.getString("b.korisnicko_ime"),
                rs.getString("b.lozinka_hash"),
                rs.getString("b.ime"),
                rs.getString("b.prezime"),
                rs.getString("b.uloga"),
                rs.getBoolean("b.active")
            );
            list.add(new Pozajmica(
                rs.getLong("p.id"),
                c,
                b,
                rs.getTimestamp("p.datum_izdavanja").toLocalDateTime(),
                rs.getDate("p.rok_vracanja").toLocalDate(),
                rs.getString("p.status"),
                rs.getBigDecimal("p.ukupna_kazna"),
                rs.getString("p.napomena")
            ));
        }
        rs.close(); return list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public LocalDateTime getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDateTime datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public LocalDate getRokVracanja() {
        return rokVracanja;
    }

    public void setRokVracanja(LocalDate rokVracanja) {
        this.rokVracanja = rokVracanja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getUkupnaKazna() {
        return ukupnaKazna;
    }

    public void setUkupnaKazna(BigDecimal ukupnaKazna) {
        this.ukupnaKazna = ukupnaKazna;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
    
}
