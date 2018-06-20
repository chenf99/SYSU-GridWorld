/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
    private int[] turnTimes = new int[4];
    private int times;
   //constructor
    public DancingBug(int[] turntime)
    {
        this.times = 0;
        //give the array the values
        System.arraycopy(turntime, 0, turnTimes, 0, 4);
        
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        for (int i = 0; i < turnTimes[times]; ++i) {
            turn();
        }
        times = (times + 1) % 4;
        super.act();
    }
}
