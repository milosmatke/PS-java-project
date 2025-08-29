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
public class Bibliotekar implements AbstractDomainObject{
    
   private long id;
    private String korisnickoIme; // korisnicko_ime
    private String lozinkaHash;   // lozinka_hash
    private String ime;
    private String prezime;
    private String uloga;         // 'bibliotekar','admin'
    private boolean active;

    public Bibliotekar() {}

    public Bibliotekar(long id, String korisnickoIme, String lozinkaHash,
                       String ime, String prezime, String uloga, boolean active) {
        this.id=id; this.korisnickoIme=korisnickoIme; this.lozinkaHash=lozinkaHash;
        this.ime=ime; this.prezime=prezime; this.uloga=uloga; this.active=active;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bibliotekar other = (Bibliotekar) obj;
        return this.id == other.id;
    }
    
    
    
    @Override
    public String getTableName() {
       return " bibliotekar ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (korisnicko_ime, lozinka, ime, prezime, uloga, active) ";
    }

    @Override
    public String getInsertValues() {
        return "'" + korisnickoIme + "', '" + lozinkaHash + "', '" + ime + "', '" + prezime + "', '" + uloga + "', " + (active?1:0);
    }

    @Override
    public String getUpdateValues() {
        return " korisnicko_ime='" + korisnickoIme + "'" +
               ", lozinka='" + lozinkaHash + "'" +
               ", ime='" + ime + "'" +
               ", prezime='" + prezime + "'" +
               ", uloga='" + uloga + "'" +
               ", active=" + (active?1:0);
    }

    @Override
    public String getPrimaryKeyValue() {
        return " id=" + id;
    }

    @Override
    public String alias() {
       return " b ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Bibliotekar(
                rs.getLong("id"),
                rs.getString("korisnicko_ime"),
                rs.getString("lozinka"),
                rs.getString("ime"),
                rs.getString("prezime"),
                rs.getString("uloga"),
                rs.getBoolean("active")
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinkaHash() {
        return lozinkaHash;
    }

    public void setLozinkaHash(String lozinkaHash) {
        this.lozinkaHash = lozinkaHash;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
    
}
