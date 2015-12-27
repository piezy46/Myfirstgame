/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Anon
 */
public class Spinner {
    public int x = 400-5,y = 300-5;
    static int sizeX = 40,sizeY = 40;
    public static String direction = "up";
    public int health = 100;
    
    public void outline(Texture tex)
    {
        tex.bind();
        GL11.glBegin(GL_QUADS);
            glTexCoord2f(0,1);
            glVertex2i(x,y);
            glTexCoord2f(1,1);
            glVertex2i(x+sizeX,y);
            glTexCoord2f(1,0);
            glVertex2i(x+sizeX,y+sizeY);
            glTexCoord2f(0,0);
            glVertex2i(x,y+sizeY);
        GL11.glEnd();
        System.out.println("X - "+x+"Y - "+y);
    }
    
    public void draw(Texture tex){
        //outline();
        tex.bind();
        GL11.glBegin(GL_QUADS);
            glTexCoord2f(0,1);
            glVertex2i(x,y);
            glTexCoord2f(1,1);
            glVertex2i(x+sizeX,y);
            glTexCoord2f(1,0);
            glVertex2i(x+sizeX,y+sizeY);
            glTexCoord2f(0,0);
            glVertex2i(x,y+sizeY);
        GL11.glEnd();
        
    }
    public void drawSpinner(){
        
        GL11.glBegin(GL_QUADS);
            glVertex2i(x,y);
            glVertex2i(x+sizeX,y);
            glVertex2i(x+sizeX,y+sizeY);
            glVertex2i(x,y+sizeY);
        GL11.glEnd();
    }
    
}
