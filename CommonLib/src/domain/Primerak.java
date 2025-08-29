/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class Primerak implements AbstractDomainObject{
    
    private long id;
    private Knjiga knjiga;
    private String inventarskiBroj;
    private String stanje;      // 'ispravan','ostecen','izgubljen'
    private String dostupnost;  // 'slobodan','zaduzen','rezervisan'
    private String lokacija;

    public Primerak() {}

    public Primerak(long id, Knjiga knjiga, String inventarskiBroj, String stanje, String dostupnost, String lokacija) {
        this.id = id; this.knjiga = knjiga; this.inventarskiBroj = inventarskiBroj;
        this.stanje = stanje; this.dostupnost = dostupnost; this.lokacija = lokacija;
    }
    

    @Override
    public String getTableName() {
        return " primerak ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (knjiga_id, inventarski_broj, stanje, dostupnost, lokacija) ";
    }

    @Override
    public String getInsertValues() {
        return knjiga.getId() + ", '" + inventarskiBroj + "', '" + stanje + "', '" + dostupnost + "', " +
               (lokacija==null? "NULL" : "'" + lokacija + "'");
    }

    @Override
    public String getUpdateValues() {
        return " knjiga_id=" + knjiga.getId() +
               ", inventarski_broj='" + inventarskiBroj + "'" +
               ", stanje='" + stanje + "'" +
               ", dostupnost='" + dostupnost + "'" +
               ", lokacija=" + (lokacija==null? "NULL" : "'" + lokacija + "'");
    }

    @Override
    public String getPrimaryKeyValue() {
        return " id=" + id;
    }

    @Override
    public String alias() {
        return " pr ";
    }

    @Override
    public String join() {
        return " JOIN knjiga k ON k.id = pr.knjiga_id ";
    }

    @Override
    public String condition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            list.add(new Primerak(
                rs.getLong("pr.id"),
                k,
                rs.getString("pr.inventarski_broj"),
                rs.getString("pr.stanje"),
                rs.getString("pr.dostupnost"),
                rs.getString("pr.lokacija")
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

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public String getInventarskiBroj() {
        return inventarskiBroj;
    }

    public void setInventarskiBroj(String inventarskiBroj) {
        this.inventarskiBroj = inventarskiBroj;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    public String getDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(String dostupnost) {
        this.dostupnost = dostupnost;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
    
}
