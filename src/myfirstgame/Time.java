/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

/**
 *
 * @author Anon
 */

public class Time 
{
    
    public static final long SECOND = 1000000000L;

    
    public static long getTime()
    {
        return System.nanoTime(); //divide 1 nanosec by a thousand millions - 0.0000000001, and let it increase til it is 1. thats when one second has passed.
    }
    
}
