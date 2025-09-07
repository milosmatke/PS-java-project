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
public class Knjiga implements AbstractDomainObject{
    
    private long id;
    private String naslov;
    private String izdavac;
    private int godinaIzdanja;     
    private String zanr;
    private Autor autor;
    private int kolicina;

    public Knjiga() {
    }

    public Knjiga(long id, String naslov, String izdavac, int godinaIzdanja, String zanr, Autor autor, int kolicina) {
        this.id = id;
        this.naslov = naslov;
        this.izdavac = izdavac;
        this.godinaIzdanja = godinaIzdanja;
        this.zanr = zanr;
        this.autor = autor;
        this.kolicina=kolicina;
    }

    
   
    

    @Override
    public String toString() {
       return naslov+": "+ autor.getIme()+" "+autor.getPrezime();
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
        return " (naslov, izdavac, kolicina, autor_id, godinaIzdanja,zanr) ";
    }

    @Override
    public String getInsertValues() {
        return "'" + naslov + "', '" + (izdavac==null?"":izdavac) + "', " +
                kolicina + ", " +
                autor.getId() + ", " +
               godinaIzdanja + ", " +
               (zanr==null? "NULL" : "'" + zanr + "'");
    }

    @Override
    public String getUpdateValues() {
        return  "naslov='"+naslov+"', izdavac='"+ izdavac+"', kolicina='"+ kolicina+ "', autor_id='"+ autor.getId()+
                "', godinaIzdanja='"+ godinaIzdanja+"', zanr='" + zanr + "'";
                         
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
            Knjiga knjiga= new Knjiga(rs.getLong("id"),  rs.getString("naslov"),  rs.getString("izdavac"),
                    rs.getInt("godinaIzdanja") , rs.getString("zanr"), autor, rs.getInt("kolicina"));

            

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

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public String getZanr() {
        return zanr;
    }

    public void setKategorija(String kategorija) {
        this.zanr = kategorija;
    }

    

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

   

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
}
