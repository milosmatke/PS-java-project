/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    
    public ServerThread() throws IOException {
        serverSocket=new ServerSocket(9000);
    }
    
    @Override
    public void run() {
        while(!serverSocket.isClosed()){
            try {
                System.out.println("Waiting for client...");
                Socket socket=serverSocket.accept();
                HandleClientThread clientThread=new HandleClientThread(socket);
                clientThread.start();
                System.out.println("Client connected");
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
