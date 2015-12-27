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
public class Tree {
    public static int treex;
    public static int treey;
    static int sizeX = 45,sizeY = 45;
    
    public static void spawnTree(int x,int y){
        treex = x;
        treey = y;
    }
    
    
    public void outline(){
        GL11.glLineWidth((float)2.5);
        GL11.glColor3f(255, 0, 0);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(treex, treey);
        GL11.glVertex2f(treex, treey+sizeY);
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(treex, treey+sizeY);
        GL11.glVertex2f(treex+sizeX, treey+sizeY);
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(treex+sizeX, treey+sizeY);
        GL11.glVertex2f(treex+sizeX, treey);
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(treex+sizeX, treey);
        GL11.glVertex2f(treex, treey);
        GL11.glEnd();
        
        GL11.glColor3f(255, 255, 255);

    }
    
    public void drawTree(Texture tex){
        outline();
        tex.bind();
        GL11.glBegin(GL_QUADS);
            glTexCoord2f(0,1);
            glVertex2i(treex,treey);
            glTexCoord2f(1,1);
            glVertex2i(treex+sizeX,treey);
            glTexCoord2f(1,0);
            glVertex2i(treex+sizeX,treey+sizeY);
            glTexCoord2f(0,0);
            glVertex2i(treex,treey+sizeY);
        GL11.glEnd();
    }
    public static void drawTree(){
        
        GL11.glBegin(GL_QUADS);
            glVertex2i(treex,treey);
            glVertex2i(treex+sizeX,treey);
            glVertex2i(treex+sizeX,treey+sizeY);
            glVertex2i(treex,treey+sizeY);
        GL11.glEnd();
    }
}
