/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DBBroker;
import domain.AbstractDomainObject;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public abstract class AbstractSO {
    protected abstract void validate(AbstractDomainObject ado) throws Exception;
    protected abstract void execute(AbstractDomainObject ado) throws Exception;
    
    public void templateExecute(AbstractDomainObject ado) throws Exception{
       try{
           validate(ado);
           execute(ado);
           commit();
       }catch(Exception e){
           System.out.println("Desilo se");
           rollback();
           throw e;
       }
    }

    private void commit() throws SQLException{
        DBBroker.getInstance().getConnection().commit();
    }

    private void rollback() throws SQLException {
        System.out.println("Rolbek sranje");
        DBBroker.getInstance().getConnection().rollback();
    }
}
