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
    private String naslov;
    private String izdavac;
    private Integer godinaIzdanja;     // YEAR
    private String zanr;
    private Autor autor;
    private boolean dostupna;

    // M:N → lista autora (puni se kroz JOIN na knjiga_autor + autor)
    private List<Autor> autori = new ArrayList<>();

    public Knjiga() {
    }

    public Knjiga(long id, String naslov, String izdavac, Integer godinaIzdanja, String zanr, Autor autor, boolean dostupna) {
        this.id = id;
        this.naslov = naslov;
        this.izdavac = izdavac;
        this.godinaIzdanja = godinaIzdanja;
        this.zanr = zanr;
        this.autor = autor;
        this.dostupna = dostupna;
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
        return " (naslov, izdavac, dostupna, autor_id, godinaIzdanja,zanr) ";
    }

    @Override
    public String getInsertValues() {
        return "'" + naslov + "', '" + (izdavac==null?"":izdavac) + "', " +
                (dostupna ? 1 : 0) + ", " +
                autor.getId() + ", " +
               (godinaIzdanja==null? "NULL" : godinaIzdanja) + ", " +
               (zanr==null? "NULL" : "'" + zanr + "'");
    }

    @Override
    public String getUpdateValues() {
        return "naslov='" + naslov + "'" +
               ", izdavac=" + (izdavac==null? "NULL" : "'" + izdavac + "'") +
                ", dostupna=" + (dostupna ? 1 : 0) +
                ", autor_id=" + autor.getId()+
               ", godina_izdanja=" + (godinaIzdanja==null? "NULL" : godinaIzdanja) +
               ", zanr=" + (zanr==null? "NULL" : "'" + zanr + "'");
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
        return "INNER JOIN Autor a ON (k.autor_id = a.id) ";
    }

    @Override
    public String condition() {
       return "";
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Autor autor = new Autor(rs.getLong("id"),
                    rs.getString("ime"), rs.getString("prezime"));
            Knjiga knjiga= new Knjiga(rs.getLong("id"),  rs.getString("naslov"),  rs.getString("izdavac"),rs.getInt("godinaIzdanja") , rs.getString("zanr"), autor, rs.getBoolean("dostupna"));

            

            lista.add(knjiga);
        }

        rs.close();
        return lista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getZanr() {
        return zanr;
    }

    public void setKategorija(String kategorija) {
        this.zanr = kategorija;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
}
