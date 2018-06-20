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
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private int courage;
    private static final double DARKENING_FACTOR = 0.05;
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */

    public BlusterCritter(int c) {
        courage = c;
    }

    public void processActors(ArrayList<Actor> actors)
    {
        int count = 0;
        for (Actor a : actors) {
            if (a instanceof Critter) {
                count++;
            }
        }
        if (count < courage) {
            colorBrighter();
        }
        else {
            colorDarken();
        }
    }

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        for (Location neighborLoc : getLocations()) {
            Actor actor = getGrid().get(neighborLoc);
            if (actor != null) {
                neighbors.add(actor);
            }
        }
        return neighbors;
    }
    //get the 24 locations
    private ArrayList<Location> getLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (int r = getLocation().getRow() - 2; r <= getLocation().getRow() + 2; ++r) {
            for (int c = getLocation().getCol() - 2; c <= getLocation().getCol() + 2; ++c) {
                Location loc = new Location(r, c);
                if (getGrid().isValid(loc) && loc != getLocation()) {
                    locs.add(loc);
                }
            }
        }
        return locs;
    }
    //darken the colors
    private void colorDarken() {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));
    }

    //brighter the colors
    private void colorBrighter() {
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        //make sure no conflicts
        if (red < 255) {
            red++;
        }
        if (green < 255) {
            green++;
        }
        if (blue < 255) {
            blue++;
        }

        setColor(new Color(red, green, blue));
    }

}
