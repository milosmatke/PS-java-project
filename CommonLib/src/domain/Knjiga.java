/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Korisnik
 */
public class Knjiga implements AbstractDomainObject{
    
    private long id;
    private String isbn;
    private String naslov;
    private String izdavac;
    private Integer godinaIzdanja;     // YEAR
    private String kategorija;
    private String signatura;
    private boolean dostupna;

    // M:N → lista autora (puni se kroz JOIN na knjiga_autor + autor)
    private List<Autor> autori = new ArrayList<>();

    public Knjiga() {}

    public Knjiga(long id, String isbn, String naslov, String izdavac,
                  Integer godinaIzdanja, String kategorija, String signatura, boolean dostupna) {
        this.id = id; this.isbn = isbn; this.naslov = naslov; this.izdavac = izdavac;
        this.godinaIzdanja = godinaIzdanja; this.kategorija = kategorija;
        this.signatura = signatura; this.dostupna = dostupna;
    }
    

    @Override
    public String toString() {
       return naslov+" "+ izdavac;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Knjiga other = (Knjiga) obj;
        return this.id == other.id;
    }
    
    
   

    @Override
    public String getTableName() {
        return " knjiga ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (isbn, naslov, izdavac, godina_izdanja, kategorija, signatura, dostupna) ";
    }

    @Override
    public String getInsertValues() {
        return "'" + (isbn==null?"":isbn) + "', '" + naslov + "', '" + (izdavac==null?"":izdavac) + "', " +
               (godinaIzdanja==null? "NULL" : godinaIzdanja) + ", " +
               (kategorija==null? "NULL" : "'" + kategorija + "'") + ", " +
               (signatura==null? "NULL" : "'" + signatura + "'") + ", " +
               (dostupna ? 1 : 0);
    }

    @Override
    public String getUpdateValues() {
        return " isbn=" + (isbn==null? "NULL" : "'" + isbn + "'") +
               ", naslov='" + naslov + "'" +
               ", izdavac=" + (izdavac==null? "NULL" : "'" + izdavac + "'") +
               ", godina_izdanja=" + (godinaIzdanja==null? "NULL" : godinaIzdanja) +
               ", kategorija=" + (kategorija==null? "NULL" : "'" + kategorija + "'") +
               ", signatura=" + (signatura==null? "NULL" : "'" + signatura + "'") +
               ", dostupna=" + (dostupna ? 1 : 0);
    }

    @Override
    public String getPrimaryKeyValue() {
         return " id=" + id;
    }

    @Override
    public String alias() {
        return " k ";
    }

    @Override
    public String join() {
        return " LEFT JOIN knjiga_autor ka ON ka.knjiga_id = k.id " +
               " LEFT JOIN autor a ON a.id = ka.autor_id ";
    }

    @Override
    public String condition() {
       return "";
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        // grupišemo po k.id da ne dupliramo knjigu kada ima više autora
        Map<Long, Knjiga> map = new LinkedHashMap<>();
        while (rs.next()) {
            long kId = rs.getLong("k.id");
            Knjiga k = map.get(kId);
            if (k == null) {
                k = new Knjiga(
                    kId,
                    rs.getString("k.isbn"),
                    rs.getString("k.naslov"),
                    rs.getString("k.izdavac"),
                    (Integer) (rs.getObject("k.godina_izdanja") == null ? null : rs.getInt("k.godina_izdanja")),
                    rs.getString("k.kategorija"),
                    rs.getString("k.signatura"),
                    rs.getBoolean("k.dostupna")
                );
                map.put(kId, k);
            }
            Long aId = (Long) rs.getObject("a.id");
            if (aId != null) {
                Autor a = new Autor(aId, rs.getString("a.ime"), rs.getString("a.prezime"));
                // izbegni duplikate autora
                boolean exists = false;
                for (Autor ex : k.getAutori()) {
                    if (ex.getId() == a.getId()) { exists = true; break; }
                }
                if (!exists) k.getAutori().add(a);
            }
        }
        return new ArrayList<>(map.values());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public Integer getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(Integer godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    public boolean isDostupna() {
        return dostupna;
    }

    public void setDostupna(boolean dostupna) {
        this.dostupna = dostupna;
    }

    public List<Autor> getAutori() {
        return autori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }
    
}
