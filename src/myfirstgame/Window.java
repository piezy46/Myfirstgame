/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Anon
 */
public class Window {
    
    private static int width = 0;
    private static int height = 0;
    public static boolean closeReq = false;
    
    public void init(int width, int height)
    {
        
        
        Window.width = width;
        Window.height = height;
        
        try{ //Look for a displaymode with matching width and height that supports fullscreen, set it to the displaymode we want to use.
            DisplayMode displayMode = null;
            DisplayMode[] modes = Display.getAvailableDisplayModes();

            for (int i = 0; i < modes.length; i++)
            {
            if (modes[i].getWidth() == width && modes[i].getHeight() == height && modes[i].isFullscreenCapable())
            {
                displayMode = modes[i];
            }
            }
            Display.setDisplayMode(displayMode);
            Display.setFullscreen(true);
            Display.create();
            
            
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0,800,0,600,1,-1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTexParameterf( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        
        try {
            Mouse.create();
            Keyboard.create();
        }
        catch (LWJGLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Mouse.setGrabbed(true);
    }
    
    public static void update()
    {
        if(Display.isCloseRequested())
        {
            closeReq = true;
        }
        Display.update(); //Update display content and look for input. 
        Display.sync(60); //Wait til we reach 60 fps :)
        
        
    }
    
    public static void cleanUp()
    {
        Display.destroy();
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
}
