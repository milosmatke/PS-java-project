/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.AbstractDomainObject;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Korisnik
 */
public class DBBroker {
     private static  DBBroker instance;
    private Connection connection;

    public DBBroker() {
        try {
            Properties properties=new Properties();
            properties.load(new FileInputStream("dbConfig.properties"));
            
            //String url="jdbc:mysql://localhost:3306/biblioteka";
            String url="jdbc:mysql://localhost:3306/"+properties.getProperty("url");
            String username=properties.getProperty("user");
            String password=properties.getProperty("password");
            
            
            
            connection=DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() {
        if(instance==null){
            instance=new DBBroker();
        }
        return instance;
    }
    
    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException{
        String upit="SELECT * FROM " + ado.getTableName() + " " + ado.alias()
                + " " + ado.join() + " " + ado.condition();
        System.out.println(upit);
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        return (ArrayList<AbstractDomainObject>) ado.getAll(rs);
    }
    
    public PreparedStatement insert(AbstractDomainObject ado) throws SQLException {
        String upit = "INSERT INTO " + ado.getTableName() + " "
                + ado.getColumnNamesForInsert() + " VALUES(" + ado.getInsertValues() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(AbstractDomainObject ado) throws SQLException {
        String upit = "UPDATE " + ado.getTableName() + " SET "
                + ado.getUpdateValues() + " WHERE " + ado.getPrimaryKeyValue();
        System.out.println(upit);
	Statement st = connection.createStatement();
	st.executeUpdate(upit);
    }

    public void delete(AbstractDomainObject ado) throws SQLException {
        String upit = "DELETE FROM " + ado.getTableName() + " WHERE " + ado.getPrimaryKeyValue();
        System.out.println(upit);
	Statement st = connection.createStatement();
	st.executeUpdate(upit);
    }
}
