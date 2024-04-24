package Pj6;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {

	Town gaithersburg;
	Town rockville;
	Town silverSpring;
	Town clinton;
	Town waldorf;

	Road shadyGroveRoad;
	Road icc200;
	Road randolphRoad;
	Road i495;
	Road someRoad;

	@Before
	public void setUp() throws Exception {
		gaithersburg = new Town("Gaithersburg");
		rockville = new Town("Rockville");
		silverSpring = new Town("Silver Spring");
		clinton = new Town("Clinton");
		waldorf = new Town("Waldorf");

		shadyGroveRoad = new Road(gaithersburg, rockville, 3, "Shady Grove Road");
		icc200 = new Road(gaithersburg, silverSpring, 8, "ICC 200");
		randolphRoad = new Road(rockville, silverSpring, 5, "Randolph Road");
		i495 = new Road(silverSpring, clinton, 10, "I-495");
		someRoad = new Road(clinton, waldorf, 3, "Some road");
	}

	@After
	public void tearDown() throws Exception {
		gaithersburg = rockville = silverSpring = clinton = waldorf = null;
		shadyGroveRoad = icc200 = randolphRoad = i495 = someRoad = null;
	}

	@Test
	public void containsTest() {
		assertTrue(shadyGroveRoad.contains(gaithersburg));
		assertTrue(shadyGroveRoad.contains(rockville));
		assertFalse(someRoad.contains(silverSpring));

		assertTrue(randolphRoad.contains(rockville));
		assertFalse(randolphRoad.contains(waldorf));
	}

	@Test
	public void equalsTest() {
		Road shadyGroveRoadDuplicate = new Road(gaithersburg, rockville, 3, "Shady Grove Road");
		assertTrue(shadyGroveRoad.equals(shadyGroveRoadDuplicate));
		assertFalse(randolphRoad.equals(shadyGroveRoad));
		assertFalse(i495.equals(someRoad));
		assertFalse(shadyGroveRoadDuplicate.equals(i495));
	}

	@Test
	public void getDestinationTest() {
		assertTrue(shadyGroveRoad.getDestination().getName().equals("Rockville"));
		assertTrue(icc200.getDestination().getName().equals("Silver Spring"));
		assertFalse(randolphRoad.getDestination().getName().equals("Waldorf"));
	}
	
	

} 
