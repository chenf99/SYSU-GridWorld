package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private Location next;
	private Location last;
	private boolean isEnd = false;
	private Stack<Location> crossLocation = new Stack<Location>();
	private Integer stepCount = 0;
	private boolean hasShown = false;//final message has been shown
	private Boolean[][] visited = new Boolean[200][200];

	int probability[] = {0, 0, 0, 0};
	private void increasePro(int direction) {
		probability[direction / 90]++;
	}
	private void decreasePro(int direction) {
		probability[direction / 90]--;
	}
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		for (int i = 0; i < 200; ++i) {
			for (int j = 0; j < 200; ++j) {
				visited[i][j] = new Boolean(false);
			}
		}
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (stepCount == 0) {
			Location loc = getLocation();
			visited[loc.getRow()][loc.getCol()] = true;
			crossLocation.push(loc);
		}
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else {
			crossLocation.pop();
			decreasePro(getDirection());
			backTracking();
			stepCount++;
		}
	}
	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		int direction = Location.NORTH;
		for (int i = 0; i < 4; ++i) {
			Location location = loc.getAdjacentLocation(direction);
			if (location!= null && gr.isValid(location)) {
				//get to end
				if (gr.get(location) instanceof Rock && gr.get(location).getColor().equals(Color.RED)) {
					isEnd = true;
					valid.add(location);
					setDirection(getLocation().getDirectionToward(location));
					moveTo(location);
				}
				else if (visited[location.getRow()][location.getCol()] == false && (gr.get(location) == null || gr.get(location) instanceof Flower)) {
					valid.add(location);
				}
			}
			direction += Location.RIGHT;
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		return getValid(getLocation()).size() > 0;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}

		Location loc = getLocation();
		ArrayList<Location> getValid = getValid(loc);
		next = null;
		if (getValid.size() > 0) {
			next = getLocationByPro(getValid);
		}
		
		if (next != null && gr.isValid(next)) {
			setDirection(loc.getDirectionToward(next));
			moveTo(next);
			visited[next.getRow()][next.getCol()] = true;
			crossLocation.push(next);
			increasePro(getDirection());
		} else {
			removeSelfFromGrid();return;
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);

		
	}

	private void backTracking() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		
		if (!crossLocation.empty()) {
			Location loc = crossLocation.peek();
			if (gr.isValid(loc)) {
				setDirection(getLocation().getDirectionToward(loc));
				moveTo(loc);
			}
		}
	}

	private Location getLocationByPro(ArrayList<Location> locations) {
		int direction = getLocation().getDirectionToward(locations.get(0));
		int max = probability[direction / 90];
		int index = 0;

		for (int i = 1; i < locations.size(); ++i) {
			int direction_ = getLocation().getDirectionToward(locations.get(i));
			int temp = probability[direction_ / 90];
			if (max < temp) {
				max = temp;
				index = i;
				direction = direction_;
			}
		}

		double randomNumber = (double)Math.random();
		if (randomNumber >= 0 && randomNumber < 0.6) {
			return locations.get(index);
		}
		else {
			int random = (int)(Math.random() * locations.size());
			return locations.get(random);
		}
	}
}
