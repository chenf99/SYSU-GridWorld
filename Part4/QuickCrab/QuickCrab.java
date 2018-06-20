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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    public QuickCrab()
    {
        setColor(Color.RED);
    }

    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            {getDirection() + Location.LEFT, getDirection() + Location.RIGHT };
        for (int i = 0; i < 2; ++i) {
            Location loc1 = getLocation().getAdjacentLocation(dirs[i]);
            if (getGrid().isValid(loc1) && getGrid().get(loc1) == null) {
                Location loc2 = loc1.getAdjacentLocation(dirs[i]);
                if (getGrid().isValid(loc2) && getGrid().get(loc2) == null) {
                    locs.add(loc2);
                }
            }
        }
        if (locs.size() == 0) {
            return super.getMoveLocations();
        }
        return locs;
    }
}
