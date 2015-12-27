/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glVertex2i;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Anon
 */
public class toolBar {
    
    int x = 0,y = 200;
    public int slot1 = 0;
    public int slot2 = 0;
    public int slot3 = 0;
    
    
    public void draw(Texture tex)
    {
        
        tex.bind();
        GL11.glBegin(GL_QUADS);
            GL11.glTexCoord2f(0,1);
            glVertex2i(x,y);
            GL11.glTexCoord2f(1,1);
            glVertex2i(x+45,y);
            GL11.glTexCoord2f(1,0);
            glVertex2i(x+45,y+250);
            GL11.glTexCoord2f(0,0);
            glVertex2i(x,y+250);
        GL11.glEnd();
    }
    
}
