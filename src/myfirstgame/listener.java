/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anon
 */
public class listener extends Thread{
    
    public void run()
    {
        while(true)
        {
            try {
                String msg = connectionHandler.inFromServer.readLine(); // waits for a message to arrive - needs its own thread
                if (msg != null)
                    System.out.println(msg);
            }
            catch (IOException ex) {
                Logger.getLogger(listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
