/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import static myfirstgame.Spinner.sizeX;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
import org.newdawn.slick.opengl.Texture;


     

/**
 *
 * @author Anon
 */
public class symbol {
    
    static int sizeX = Myfirstgame.screenX/50,sizeY = Myfirstgame.screenY/25;
    int x = 200, y = 200;
    Texture texture;
    
    public void draw(){
        texture.bind();
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
    
}
