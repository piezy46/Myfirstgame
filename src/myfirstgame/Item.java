/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Anon
 */
public abstract class Item 
{
    public int slot = 0;
    public abstract String getName();
    public abstract String getDesc();
    public abstract int getX();
    public abstract int getY();
    public abstract int getSizeX();
    public abstract int getSizeY();
    
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract int getId();
    public abstract boolean isWearable();
    public abstract void use();   
    
    public abstract void draw(Texture tex);
    public abstract void drop();
    
    
}
