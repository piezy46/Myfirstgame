/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Anon
 */
public class cursor {
    public void draw(Texture tex)
    {
         tex.bind();
        GL11.glBegin(GL_QUADS);
            glTexCoord2f(0,1);
            glVertex2i(Mouse.getX()-12,Mouse.getY()-12);
            glTexCoord2f(1,1);
            glVertex2i(Mouse.getX()-12+25,Mouse.getY()-12);
            glTexCoord2f(1,0);
            glVertex2i(Mouse.getX()-12+25,Mouse.getY()-12+25);
            glTexCoord2f(0,0);
            glVertex2i(Mouse.getX()-12,Mouse.getY()-12+25);
        GL11.glEnd();
    }
}
