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
public abstract class entity {

    int hp;
    int mana;
    int ammo;
    int damage;

    int x;
    int y;
    int sizeX;
    int sizeY;

    public abstract void draw(Texture tex);

    public abstract void drawHP(Texture tex);

}
