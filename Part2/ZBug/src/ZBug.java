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


public class ZBug extends Bug
{
    private int steps;
    private int sideLength;//the length of the "Z" pattern
    private int side;

    
    public ZBug(int length)
    {
        steps = 0;
        side = 1;
        sideLength = length;
        setDirection(90);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        //don't act when complete
        if (side == 4) {
            return;
        }
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else if (steps == sideLength)//set how to change the direction
        {
            if (side == 1) {
                turn();
                turn();
                turn();
            }
            else if (side == 2){
                for (int i = 0; i < 5; ++i) {//turn 5 times
                    turn();
                }
            }
            side++;
            steps = 0;
        }
    }
}
