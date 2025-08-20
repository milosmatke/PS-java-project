/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

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
public class Clan implements AbstractDomainObject{
   private long id;
    private String brojClanske;   // broj_clanske
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private LocalDate datumIsteka;
    private String status;        // 'aktivan','neaktivan','banovan'
    private LocalDateTime createdAt;

    public Clan() {}

    public Clan(long id, String brojClanske, String ime, String prezime, String email,
                String telefon, LocalDate datumIsteka, String status, LocalDateTime createdAt) {
        this.id=id; this.brojClanske=brojClanske; this.ime=ime; this.prezime=prezime;
        this.email=email; this.telefon=telefon; this.datumIsteka=datumIsteka;
        this.status=status; this.createdAt=createdAt;
    }

    

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Clan other = (Clan) obj;
        return this.id == other.id;
    }
    
    

    @Override
    public String getTableName() {
       return " clan ";
    }

    @Override
    public String getColumnNamesForInsert() {
       return " (broj_clanske, ime, prezime, email, telefon, datum_isteka, status) ";
    }

    @Override
    public String getInsertValues() {
       return "'" + brojClanske + "', '" + ime + "', '" + prezime + "', " +
               (email==null? "NULL" : "'" + email + "'") + ", " +
               (telefon==null? "NULL" : "'" + telefon + "'") + ", " +
               "'" + datumIsteka + "', '" + status + "'";
    }

    @Override
    public String getUpdateValues() {
        return " broj_clanske='" + brojClanske + "'" +
               ", ime='" + ime + "'" +
               ", prezime='" + prezime + "'" +
               ", email=" + (email==null? "NULL" : "'" + email + "'") +
               ", telefon=" + (telefon==null? "NULL" : "'" + telefon + "'") +
               ", datum_isteka='" + datumIsteka + "'" +
               ", status='" + status + "'";
    }

    @Override
    public String getPrimaryKeyValue() {
        return " id=" + id;
    }

    @Override
    public String alias() {
        return " c ";
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
            list.add(new Clan(
                rs.getLong("id"),
                rs.getString("broj_clanske"),
                rs.getString("ime"),
                rs.getString("prezime"),
                rs.getString("email"),
                rs.getString("telefon"),
                rs.getDate("datum_isteka").toLocalDate(),
                rs.getString("status"),
                rs.getTimestamp("created_at").toLocalDateTime()
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

    public String getBrojClanske() {
        return brojClanske;
    }

    public void setBrojClanske(String brojClanske) {
        this.brojClanske = brojClanske;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public LocalDate getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(LocalDate datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
