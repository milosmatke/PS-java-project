/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.Bibliotekar;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Korisnik
 */
public class Communication {
    private static Communication instance;
    private Socket socket;
    private Bibliotekar ulogovani;

    public Communication() {
        try {
            socket=new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Communication getInstance() {
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public Bibliotekar getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Bibliotekar ulogovani) {
        this.ulogovani = ulogovani;
    }
}
