/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.text.Font.font;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.AL_GAIN;
import static org.lwjgl.openal.AL10.AL_PAUSED;
import static org.lwjgl.openal.AL10.AL_PLAYING;
import static org.lwjgl.openal.AL10.AL_SOURCE_STATE;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alGenBuffers;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alGetSourcei;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourcef;
import static org.lwjgl.openal.AL10.alSourcei;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glVertex2i;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.WaveData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Oscar Örtenholm (Creator of the Game)
 */
public class Game {
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    public List<bullet> bullets = new ArrayList<bullet>();
    public List<symbol> symbols = new ArrayList<symbol>();
    public List<Tree> trees = new ArrayList<Tree>();
    public List<Item> items = new ArrayList<Item>();
    
    public static Spinner player = new Spinner();
    public static grass g = new grass();
    public static toolBar tb = new toolBar();
    
    
    //public static cursor c = new cursor();
    
    public boolean showMenu = false;
    public boolean showToolBar = false;
    
    Texture treeTex; 
    Texture playerTex; 
    Texture grassTex;
    Texture plus;
    Texture menuTex;
    Texture bulletTex;
    Texture toolBox;
    Texture horseMask;
    Texture outlineBox;
    Texture cursorTex;
    // </editor-fold>
    public static int ammo = 10;
    public static boolean canShoot = true;
    connectionHandler ch = new connectionHandler();
    
    public void initGame() throws FileNotFoundException
    {
        
        
        ch.start();
        
        WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("res/Faster and LOUDER.wav")) );
        int buffer = alGenBuffers();
        alBufferData(buffer, data.format, data.data, data.samplerate);
        data.dispose();
        int source = alGenSources();
        

        float newVolume = 0.4f;
        alSourcef(source, AL_GAIN, newVolume);


        alSourcei(source, AL_BUFFER, buffer);
        alSourcePlay(source);
        
        treeTex = loadTexture("png", "Tree 2.png");
        playerTex = loadTexture("png","Soildier 1e.png");
        grassTex = loadTexture("png", "High Grass 1.png");
        plus = loadTexture("png", "plus.png");
        menuTex = loadTexture("png", "Menu.png");
        bulletTex = loadTexture("png", "bullet.png");
        toolBox = loadTexture("png", "toolBar.png");
        horseMask = loadTexture("png", "Horseface.png");
        //outlineBox = loadTexture("png", "outlineBox.png");
        cursorTex = loadTexture("png", "cursor.png");
        
        
        //Add spawn items here.
        items.add(new horsemask());
        if(tb.slot1 == 0) //TODO: Remove x and y setters when automatic positioning depending on slot has been implemented.
        {                    //REMINDER: The slot's number represent the id of the item it's holding. If the value is 0, it means its empty.
            int i = items.size()-1;
            tb.slot1 = items.get(i).getId();
            items.get(i).slot = 1;
            items.get(i).setX(5);
            items.get(i).setY(407);
        }
        else if(tb.slot2 == 0)
        {
            int i = items.size()-1;
            tb.slot2 = items.get(i).getId();
            items.get(i).slot = 2;
            items.get(i).setX(5);
            items.get(i).setY(355);
        } 
        else if(tb.slot3 == 0)
        { 
            
            int i = items.size()-1;
            tb.slot3 = items.get(i).getId();
            items.get(i).slot = 3;
            items.get(i).setX(5);
            items.get(i).setY(303); 
        }
        else
        {
            items.get(items.size()-1).drop();
        }
        
        
    }
    
    public void playGunShot() throws FileNotFoundException
    {
        WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("res/gun-gunshot-02.wav")) );
        int buffer = alGenBuffers();
        alBufferData(buffer, data.format, data.data, data.samplerate);
        data.dispose();
        int source = alGenSources();
        alSourcei(source, AL_BUFFER, buffer);
        alSourcePlay(source);
    }
    
    public void playGunReload() throws FileNotFoundException
    {
        WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("res/gun-cocking-03.wav")) );
        int buffer = alGenBuffers();
        alBufferData(buffer, data.format, data.data, data.samplerate);
        data.dispose();
        int source = alGenSources();
        alSourcei(source, AL_BUFFER, buffer);
        alSourcePlay(source);
    }
    
    public void update() throws FileNotFoundException, IOException
    {
        
        checkPlayerInput();//Check for input
        drawGrass();   //eat toilet
        doBulletStuff(); // hold on, what?
        player.draw(playerTex); //shit in toilet
        if(showToolBar)tb.draw(toolBox); //better! ...eat granola
        drawItems(); //Listen to music
        if(showMenu == true)menu(menuTex); //if banana is rotten, eat it...? :P why not
        
        
        //Everything below is for bugfixing
        //player.outline(outlineBox);
        //System.out.println("MouseX = " + Mouse.getX() + " MouseY = " + Mouse.getY());
        //c.draw(cursorTex);
        
    }
    
    public void drawItems()
    {
        for(Item i : items)
        {
            Texture tex = null;
            if(i.getId() == 1)
                tex = horseMask;
            
            if(tex != null && showToolBar)
                i.draw(tex);
            
            //System.out.println(i.getName() + " drawn at: "+i.getX()+" "+i.getY());
        }
    }
    
    
    public void menu(Texture tex)
    {
        int x = 200,y = 200;
        tex.bind();
        GL11.glBegin(GL_QUADS);
            GL11.glTexCoord2f(0,1);
            glVertex2i(x,y);
            GL11.glTexCoord2f(1,1);
            glVertex2i(x+200,y);
            GL11.glTexCoord2f(1,0);
            glVertex2i(x+200,y+200);
            GL11.glTexCoord2f(0,0);
            glVertex2i(x,y+200);
        GL11.glEnd();
    }   

    public void drawBullet()
    
    {
        for(bullet b : bullets){
            if(b != null){
                if (b.Bdirection == "right"){
                   b.drawBullet(b.posX+25, b.posY + 17,bulletTex);
                } else if (b.Bdirection == "left") {
                    b.drawBullet(b.posX+10, b.posY+17,bulletTex);
                } else if (b.Bdirection == "up") {
                    b.drawBullet(b.posX + 12, b.posY+25,bulletTex);
                } else if (b.Bdirection == "down") {
                    b.drawBullet(b.posX + 12, b.posY+25,bulletTex);
                } else {
                    System.out.println("NONE WERE TRUE");
                }      
            }
        }
    }
    
    public void doBulletStuff()
    {
                for(bullet b : bullets){
                    drawBullet();
                    b.run();  
                    //System.out.println(b.posX + " " + b.posY);
                    
                }
                
                bullet tb = checkBulletCollision();
                if(tb != null){
                    bullets.remove(tb);
                    tb = null;
                }
    }
    
    public Texture loadTexture(String filetype, String url) 
    {  
        try 
        {
            return TextureLoader.getTexture(filetype, new FileInputStream(new File("res/" + url))); 
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public bullet checkBulletCollision()
    {
        for (bullet b : bullets){
            if(b.posX >= 800){
                return(b);
            } else if(b.posX <= 0){
                return(b);
            } else if(b.posY >= 800) {
               return(b);
            } else if (b.posY <= -51){
                return(b);
            }
            
            
            
        }
        return(null);
    }
    
    public void checkPlayerInput() throws FileNotFoundException
    {
        
        if(Input.getKeyDown(Keyboard.KEY_ESCAPE))
        {
            if(showMenu == false)
            {
                showMenu = true;
            } else if(showMenu == true)
            {
                showMenu = false;
            }
               
        }
        if(Input.getKeyDown(Keyboard.KEY_E))
        {
            if(showToolBar == false)
            {
                showToolBar = true;
            } else if(showToolBar == true)
            {
                showToolBar = false;
            }
               
        }
        
            if(Input.getMouseDown(0)){
                
                    if(Mouse.getX() > 13 && Mouse.getX() < 94 && Mouse.getY() > 733 && Mouse.getY() < 799) //if mouse is inside first slot
                    {
                        System.out.println("Clicked in first slot!");
                        for(Item i : items){
                            if(i.slot == 1 && tb.slot1 == i.getId())
                            {
                               i.use();
                               System.out.println(i.getName() + " Has been used!"); 
                            }
                        }
                        // first toolbar slot
                        
                    }else if(showToolBar && Mouse.getX() > 13 && Mouse.getX() < 94 && Mouse.getY() > 638 && Mouse.getY() < 718) // if mouse is inside second slot
                    {
                        System.out.println("Clicked in second slot!");
                        for(Item i : items){
                            if(i.slot == 2 && tb.slot2 == i.getId())
                            {
                               i.use();
                               System.out.println(i.getName() + " Has been used!"); 
                            }
                        }
                        // second toolbar slot 
                    }else if(showToolBar && Mouse.getX() > 13 && Mouse.getX() < 94 && Mouse.getY() > 544 && Mouse.getY() < 624) // if mouse is inside third slot
                    {
                        System.out.println("Clicked in third slot!");
                        for(Item i : items){
                            if(i.slot == 3 && tb.slot3 == i.getId())
                            {
                               i.use();
                               System.out.println(i.getName() + " Has been used!"); 
                            }
                        }
                        // third toolbar slot 
                    }else
                
                if(showMenu && Mouse.getX() > 590 && Mouse.getX() < 839 && Mouse.getY() > 449 && Mouse.getY() < 501)
                {
                    System.out.println("quit button clicked!");
                    Myfirstgame.window.closeReq = true;
                }else{
                if(ammo > 0)
                {
                    WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("res/gun-cocking-03.wav")) );
                    int buffer = alGenBuffers();
                    alBufferData(buffer, data.format, data.data, data.samplerate);
                    data.dispose();
                    int source = alGenSources();
                    int state = alGetSourcei(source,AL_SOURCE_STATE);
                    if(canShoot)
                    {
                        playGunShot();
                        bullets.add(new bullet());
                        int i = bullets.size()-1;
                        bullets.get(i).setVar(player.x,player.y, player.direction);
                        bullets.get(i).locked = true;
                        ammo--; 
                    }
                    
                } else 
                {
                    canShoot = false;
                    Timer t = new Timer();
                    TimerTask tt  = new TimerTask() 
                    {
                        public void run()
                        {
                            
                            canShoot = true;
                            t.cancel();
                        }
                    };
                    t.scheduleAtFixedRate(tt, 1200,1000);
                    playGunReload();
                    ammo = 10;
                }
                
                }
                
            }
//            if (Keyboard.isKeyDown(Keyboard.KEY_C) && Keyboard.isKeyDown(Keyboard.KEY_A) && Keyboard.isKeyDown(Keyboard.KEY_T)){
//                t.spawnTree(500,500);
//            }
        
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                player.direction = "left";
                playerTex = loadTexture("png","Soildier 2b.png");
                for(int i = 0; i != 2; i++){
                    
                    player.x--;
                }
            }else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                player.direction = "up";
                playerTex = loadTexture("png","Soildier 1e.png");
                for(int i = 0; i != 2; i++){
                    player.y++;
                }
            }else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                player.direction = "right";
                playerTex = loadTexture("png","Soildier 1b.png");
                for(int i = 0; i != 2; i++){
                    player.x++;
                }
            }else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                player.direction = "down";
                playerTex = loadTexture("png","Soildier 1h.png");
                for(int i = 0; i != 2; i++){
                    player.y--;
                }
            } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
                //healPlus();
            }
        }
    
    public void drawGrass()
    {
        for(int i = 0; i < Myfirstgame.window.getWidth();i=i+g.sizeX){
                for(int e = 0; e < Myfirstgame.window.getHeight();e=e+g.sizeY){
                    g.drawGrass(i,e,grassTex);
                }
            }
    }
    
    public void healPlus()
    {
        symbols.add(new symbol());
        int i = symbols.size() - 1;
        symbols.get(i).texture = plus;
    }
}
