/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;


import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    private Date datumIzdavanja;
    private Date rokVracanja;
    private ArrayList<StavkaPozajmice> listaStavki;

    public Pozajmica() {}

    public Pozajmica(long id, Clan clan, Bibliotekar bibliotekar,
                     Date datumIzdavanja, Date rokVracanja,
                      ArrayList<StavkaPozajmice> listaStavki) {
        this.id=id; this.clan=clan; this.bibliotekar=bibliotekar;
        this.datumIzdavanja=datumIzdavanja; this.rokVracanja=rokVracanja;
        ; this.listaStavki=listaStavki;
    }
    

    @Override
    public String getTableName() {
        return " pozajmica ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (clan_id, bibliotekar_id, datum_izdavanja, rok_vracanja) ";
    }

    @Override
    public String getInsertValues() {
        return "'"+clan.getId() + "', ' " + bibliotekar.getId() + "', '" +
                new java.sql.Date(datumIzdavanja.getTime()) + "', '" + new java.sql.Date(rokVracanja.getTime()) + "'";
        
        
    }

    @Override
    public String getUpdateValues() {
        return "clan_id='"+ clan.getId()+"', bibliotekar_id='"+bibliotekar.getId()+"',datum_izdavanja='" + new Timestamp(datumIzdavanja.getTime()) + "', rok_vracanja='" + new Timestamp(rokVracanja.getTime());
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
                rs.getString("b.lozinka"),
                rs.getString("b.ime"),
                rs.getString("b.prezime"),
                rs.getString("b.uloga"),
                rs.getBoolean("b.active")
            );
            list.add(new Pozajmica(
                rs.getLong("p.id"),
                c,
                b,
                rs.getDate("p.datum_izdavanja"),
                rs.getDate("p.rok_vracanja"),null
               
                
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

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getRokVracanja() {
        return rokVracanja;
    }

    public void setRokVracanja(Date rokVracanja) {
        this.rokVracanja = rokVracanja;
    }

   

    public ArrayList<StavkaPozajmice> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<StavkaPozajmice> listaStavki) {
        this.listaStavki = listaStavki;
    }

    
    
}
