/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Anon
 */
public class bullet {
     int sizeX = 5,sizeY = 5;
     int posX = 400, posY = 400;
     String Bdirection = "right";
     int speed = 15;
     boolean locked = false;
    
    
    public void run(){
        for(int i = 0; i < speed; i++){
            if(Bdirection == "right"){
                    posX++;
            } else if(Bdirection == "left"){
                    posX--;
            } else if(Bdirection == "up"){
                    posY++;
            } else if(Bdirection == "down"){
                    posY--;
                    //add collision testing to all directions.
            }
        }
    }
    
    public void setVar(int x, int y, String dir){
        posX = x; posY = y; Bdirection = dir;
    }
    
    public void drawBullet(int x, int y, Texture tex){
        tex.bind();
        GL11.glBegin(GL_QUADS);
            GL11.glTexCoord2f(0, 1);    glVertex2f(x,y);
            GL11.glTexCoord2f(1, 1);    glVertex2f(x+sizeX,y);
            GL11.glTexCoord2f(1, 0);    glVertex2f(x+sizeX,y+sizeY);
            GL11.glTexCoord2f(0, 0);    glVertex2f(x,y+sizeY);
        GL11.glEnd();
    }

    
    

    
    
}
