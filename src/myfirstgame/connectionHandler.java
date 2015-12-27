/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anon
 */
public class connectionHandler extends Thread{
    
    Socket clientSocket;
    DataOutputStream outToServer;
    static BufferedReader inFromServer;
    
    public void init() throws IOException
    {
            clientSocket = new Socket("localhost", 6789);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
            outToServer.writeBytes(Game.player.x + " " + Game.player.y + '\n');
            System.out.print(Game.player.x + " " + Game.player.y + '\n');
            outToServer.writeBytes(123 + " " + 124 + '\n');
            listener l = new listener();
            l.start();
        
    }
    @Override
    public void run()
    {
        try {
            init();
            //the message waiter causes blackscreen...
        }
        catch (IOException ex) {
            Logger.getLogger(connectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true)
        {
                try{
                outToServer.writeBytes(Game.player.x + " " + Game.player.y + '\n');    
                
        
            }catch(Exception e){System.out.println(e.getMessage());}
        }
        
    }
    
}
