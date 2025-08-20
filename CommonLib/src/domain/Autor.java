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
public class Autor implements AbstractDomainObject{
    
    private long id;
    private String ime;
    private String prezime;
   

    public Autor() {}

    public Autor(long id, String ime, String prezime) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
    }
    

    @Override
    public String getTableName() {
       return " autor ";
    }

    @Override
    public String getColumnNamesForInsert() {
       return " (ime, prezime) ";
    }

    @Override
    public String getInsertValues() {
        return "'" + ime + "', '" + prezime + "'";
    }

    @Override
    public String getUpdateValues() {
        return " ime='" + ime + "', prezime='" + prezime + "'";

    }

    @Override
    public String getPrimaryKeyValue() {
         return " id=" + id;
    }

    @Override
    public String alias() {
         return " a ";
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
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Autor a = new Autor(
                    rs.getLong("id"),
                    rs.getString("ime"),
                    rs.getString("prezime")
                   
            );
            lista.add(a);
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

  

    @Override
    public String toString() {
        return ime+" "+ prezime;
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
        final Autor other = (Autor) obj;
        return this.id == other.id;
    }
    
    
}
