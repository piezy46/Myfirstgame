/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test
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
public class horsemask extends Item{
    
    private final int id = 1;
    private final String name = "Horse Mask";
    private final String desc = "Be a horse, with pride.";
    private int x; 
    private int y;
    public int sizeX = 45;
    public int sizeY = 45;
    private boolean active = false;
    
    
    
    @Override
    public void use()
    {
        if(!active)
            active = true;
        else
            active = false;
        
    }
    
    @Override
    public void draw( Texture tex ) 
    {
        tex.bind();
        GL11.glBegin( GL_QUADS );
            glTexCoord2f( 0, 1 );  glVertex2i( x, y );
            glTexCoord2f( 1, 1 );  glVertex2i( x+sizeX, y );
            glTexCoord2f( 1, 0 );  glVertex2i( x+sizeX, y+sizeY );
            glTexCoord2f( 0, 0 );  glVertex2i( x, y+sizeY );
        GL11.glEnd( );
        
        if(active)
        {
            if(Game.player.direction == "left")
            {
               tex.bind();
                GL11.glBegin( GL_QUADS );
                    glTexCoord2f( 0, 1 );  glVertex2i( Game.player.x, Game.player.y+15 );
                    glTexCoord2f( 1, 1 );  glVertex2i( Game.player.x+sizeX, Game.player.y+15 );
                    glTexCoord2f( 1, 0 );  glVertex2i( Game.player.x+sizeX, Game.player.y+15+sizeY );
                    glTexCoord2f( 0, 0 );  glVertex2i( Game.player.x, Game.player.y+15+sizeY );
                GL11.glEnd( ); 
            }else{
                tex.bind();
                GL11.glBegin( GL_QUADS );
                    glTexCoord2f( 0, 1 );  glVertex2i( Game.player.x-4, Game.player.y+15 );
                    glTexCoord2f( 1, 1 );  glVertex2i( Game.player.x-4+sizeX, Game.player.y+15 );
                    glTexCoord2f( 1, 0 );  glVertex2i( Game.player.x-4+sizeX, Game.player.y+15+sizeY );
                    glTexCoord2f( 0, 0 );  glVertex2i( Game.player.x-4, Game.player.y+15+sizeY );
                GL11.glEnd( );
            }
        }
        
    }

    @Override
    public String getName(  ) 
    {
        return name;
    }

    @Override
    public String getDesc(  ) 
    {
        return desc;    
    }

    @Override
    public int getX(  ) 
    {
        return x;
    }

    @Override
    public int getY  () 
    {
        return y;
    }

    @Override
    public void setX( int x ) 
    {
        this.x = x;
    }

    @Override
    public void setY( int y ) 
    {
        this.y = y;
    }

    @Override
    public void drop(  ) // Todo: Make dropped item actually drop on the ground, maybe wobbling a little up and down to notify people its there.
    {
        System.out.print( name + " Has been dropped!" );
    }

    @Override
    public int getId(  ) 
    {
        return id;
    }

    @Override
    public boolean isWearable(  ) 
    {
        return true;
    }

    @Override
    public int getSizeX() {
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

    
    
    
    
}
