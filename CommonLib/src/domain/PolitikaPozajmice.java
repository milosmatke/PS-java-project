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
public class PolitikaPozajmice implements AbstractDomainObject{
    private int id;          // TINYINT
    private int maxDana;
    private java.math.BigDecimal dnevnaKazna;

    public PolitikaPozajmice() {}
    public PolitikaPozajmice(int id, int maxDana, java.math.BigDecimal dnevnaKazna) {
        this.id=id; this.maxDana=maxDana; this.dnevnaKazna=dnevnaKazna;
    }
    

    @Override
    public String getTableName() {
        return " politika_pozajmice ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (id, max_dana, dnevna_kazna) ";
    }

    @Override
    public String getInsertValues() {
       return id + ", " + maxDana + ", " + dnevnaKazna.toPlainString();
    }

    @Override
    public String getUpdateValues() {
        return " max_dana=" + maxDana + ", dnevna_kazna=" + dnevnaKazna.toPlainString();
    }

    @Override
    public String getPrimaryKeyValue() {
       return " id=" + id; 
    }

    @Override
    public String alias() {
         return " pp ";
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
            list.add(new PolitikaPozajmice(
                rs.getInt("id"),
                rs.getInt("max_dana"),
                rs.getBigDecimal("dnevna_kazna")
            ));
        }
        rs.close(); return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxDana() {
        return maxDana;
    }

    public void setMaxDana(int maxDana) {
        this.maxDana = maxDana;
    }

    public java.math.BigDecimal getDnevnaKazna() {
        return dnevnaKazna;
    }

    public void setDnevnaKazna(java.math.BigDecimal dnevnaKazna) {
        this.dnevnaKazna = dnevnaKazna;
    }
    
}
