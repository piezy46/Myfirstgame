/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

/**
 *
 * @author Anon
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
public class Myfirstgame {
    
    Game game = new Game();
    public static Window window = new Window();
    
    public static int screenX = 1920, screenY = 1080;
    
    
    
    public Myfirstgame() throws FileNotFoundException, IOException {
        
        try {
            AL.create();
        }
        catch (LWJGLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        window.init(screenX,screenY);
        game.initGame();
        
        
        
        while(!window.closeReq)
        {
            
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); //important to clear depth buffer and color buffer bits when using alpha blends for transparency.
            Input.update();
            game.update();
            window.update();
            
        }
        AL.destroy();
        Window.cleanUp();
    }
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        new Myfirstgame();
    }
    
    public static void fillBackground(Texture tex)
    {
//        GL11.glColor3f(r,g,b);
//        GL11.glBegin(GL11.GL_QUADS);
//        GL11.glVertex2i(0,0);
//        GL11.glVertex2i(800,0);
//        GL11.glVertex2i(800,600);
//        GL11.glVertex2i(0,600);
//        GL11.glEnd();
//        GL11.glColor3f(255,255,255);
        
        
    }
}


