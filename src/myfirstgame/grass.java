/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Anon
 */
public class grass {
    
    public static int sizeX = 30, sizeY = 30;
    public static int gx = 250, gy = 250;
    
    public void outline(){
        GL11.glLineWidth((float)2.5);
        GL11.glColor3f(255, 0, 0);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(gx, gy);
        GL11.glVertex2f(gx, gy+sizeY);
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(gx, gy+sizeY);
        GL11.glVertex2f(gx+sizeX, gy+sizeY);
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(gx+sizeX, gy+sizeY);
        GL11.glVertex2f(gx+sizeX, gy);
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(gx+sizeX, gy);
        GL11.glVertex2f(gx, gy);
        GL11.glEnd();
        
        GL11.glColor3f(255, 255, 255);

    }
    
    public void drawGrass(int x,int y,Texture tex){
        //grass background
        gx = x; gy = y;
        
        tex.bind();
        GL11.glBegin(GL_QUADS);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex2i(gx,gy);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex2i(gx+sizeX,gy);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2i(gx+sizeX,gy+sizeY);
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2i(gx,gy+sizeY);
        GL11.glEnd();
        outline();
    }
    
}


