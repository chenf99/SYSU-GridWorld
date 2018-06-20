/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import java.util.ArrayList;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;


/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */


public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
	private SparseGridNode[] occupantArray;// the array storing the grid elements
	private int lengthCol;
	/**
     * Constructs an empty SparseBounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        occupantArray = new SparseGridNode[rows];
        lengthCol = cols;
    }

    public int getNumRows()
    {
        return occupantArray.length;
    }

    public int getNumCols()
    {
        return lengthCol;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            SparseGridNode row = occupantArray[r];
            while (row != null) {
            	Location loc = new Location(r, row.getCol());
            	theLocations.add(loc);
            	row = row.getNext();
            }
        }
        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        //get the head of the arraylist
        SparseGridNode row = occupantArray[loc.getRow()];
        while (row != null) {
        	if (row.getCol() == loc.getCol()) {
        		return (E)row.getOccupant();
        	}
        	row = row.getNext();
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        //add obj to the head of the arraylist
        SparseGridNode row = occupantArray[loc.getRow()];
        occupantArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), row);
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        // Remove the object from the grid.
        E r = get(loc);
        SparseGridNode row = occupantArray[loc.getRow()];
        if (row == null) {
        	return null;
        }
        //test the first obj
        if (row.getCol() == loc.getCol()) {
        	occupantArray[loc.getRow()] = row.getNext();
        }
        SparseGridNode next = row.getNext();
        while (next != null && next.getCol() != loc.getCol()) {
        	next = next.getNext();
        	row = row.getNext();
        }
        //find the obj
        if (next != null) {
        	row.setNext(next.getNext());
        }
        return r;
    }
}