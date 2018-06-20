import static org.junit.Assert.*;
import org.junit.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;

import java.awt.Color;


public class JumperTest {
	private ActorWorld world;
	private Jumper jumper;

	@Test
	public void testAct() {
		//test jump over rocks
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(4, 5), new Rock());
		world.step();
		assertEquals(new Location(3, 5), jumper.getLocation());

		//test turn when the location two cells in front contains a rock
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3, 5), new Rock());
		world.step();
		assertEquals(45, jumper.getDirection());

		//test jump when the location two cells in front contains a flower
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3, 5), new Flower());
		world.step();
		assertEquals(new Location(3, 5), jumper.getLocation());

		//test turn when the location two cells in front of the jumper is out of the grid
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(1, 5), jumper);
		world.step();
		assertEquals(45, jumper.getDirection());

		//test turn when the the jumper is facing an edge of the grid
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(0, 5), jumper);
		world.step();
		assertEquals(45, jumper.getDirection());

		//test turn when another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3, 5), new Actor());
		world.step();
		assertEquals(45, jumper.getDirection());

		//test turn when jumper encounters another jumper in its path
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		Jumper jumper2 = new Jumper();
		world.add(new Location(3, 5), jumper2);
		jumper2.setDirection(180);
		world.step();
		assertEquals(45, jumper.getDirection());
		assertEquals(225, jumper2.getDirection());
	}
	
	@Test
	public void testAct2() {
		//test no left flower when jumer leaves on a flower
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3, 5), new Flower());
		world.step();
		assertEquals(new Location(3, 5), jumper.getLocation());
		world.step();
		assertEquals(null, jumper.getGrid().get(new Location(3, 5)));
	}

	@Test
	public void testMove() {
		//test remove when jumper is facing an edge of the grid
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(0, 5), jumper);
		jumper.move();
		assertEquals(null, jumper.getGrid());

		//test remove when the location two cells in front of the jumper is out of the grid
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(1, 5), jumper);
		jumper.move();
		assertEquals(null, jumper.getGrid());
	}

	@Test
	public void testCanMove() {
		//test true when jump over rocks
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(4, 5), new Rock());
		assertEquals(true, jumper.canMove());

		//test false when the location two cells in front contains a rock
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3, 5), new Rock());
		assertEquals(false, jumper.canMove());

		//test true when the location two cells in front contains a flower
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3, 5), new Flower());
		assertEquals(true, jumper.canMove());

		//test false when the location two cells in front of the jumper is out of the grid
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(1, 5), jumper);
		assertEquals(false, jumper.canMove());

		//test false when the the jumper is facing an edge of the grid
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(0, 5), jumper);
		assertEquals(false, jumper.canMove());

		//test false when another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3, 5), new Actor());
		assertEquals(false, jumper.canMove());

		//test false when encounters another jumper in its path
		world = new ActorWorld();
		jumper = new Jumper();
		world.add(new Location(5, 5), jumper);
		Jumper jumper2 = new Jumper();
		world.add(new Location(3, 5), jumper2);
		jumper2.setDirection(180);
		assertEquals(false, jumper.canMove());
		assertEquals(false, jumper2.canMove());
	}
}