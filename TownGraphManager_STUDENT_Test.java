package Pj6;




import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
	
	TownGraphManager townGraphManager;

	String gaithersburg;
	String rockville;
	String silverSpring;
	String clinton;
	String waldorf;
	String ashton;
	String whiteOak;
	String burtonsville;

	@Before
	public void setUp() throws Exception {
		townGraphManager = new TownGraphManager();

		gaithersburg = "Gaithersburg";
		rockville = "Rockville";
		silverSpring = "Silver Spring";
		clinton = "Clinton";
		waldorf = "Waldorf";
		ashton = "Ashton";
		whiteOak = "White Oak";
		burtonsville = "Burtonsville";

		townGraphManager.addTown(gaithersburg);
		townGraphManager.addTown(rockville);
		townGraphManager.addTown(silverSpring);
		townGraphManager.addTown(clinton);
		townGraphManager.addTown(waldorf);
		townGraphManager.addTown(ashton);
		townGraphManager.addTown(whiteOak);
		townGraphManager.addTown(burtonsville);

		townGraphManager.addRoad(gaithersburg, rockville, 3, "Shady Grove Road");
		townGraphManager.addRoad(gaithersburg, silverSpring, 8, "ICC 200");
		townGraphManager.addRoad(rockville, silverSpring, 5, "Randolph Road");
		townGraphManager.addRoad(silverSpring, clinton, 10, "I-495");
		townGraphManager.addRoad(clinton, waldorf, 3, "Some road");
		townGraphManager.addRoad(silverSpring, burtonsville, 2, "Spencerville Road");
		townGraphManager.addRoad(silverSpring, whiteOak, 3, "New Hampshire Ave N");
		townGraphManager.addRoad(silverSpring, ashton, 1, "New Hampshire Ave S");
		townGraphManager.addRoad(gaithersburg, burtonsville, 16, "ICC 201");
	}

	@After
	public void tearDown() throws Exception {
		gaithersburg = rockville = silverSpring = clinton = waldorf = ashton = whiteOak = burtonsville = null;
		townGraphManager = null;
	}

	@Test
	public void testAddRoad() {
		townGraphManager.addRoad(clinton, gaithersburg, 200, "DNE");
		assertEquals("DNE", townGraphManager.getRoad(clinton, gaithersburg));

		townGraphManager.addRoad(gaithersburg, waldorf, 100, "Random");
		assertEquals("Random", townGraphManager.getRoad(gaithersburg, waldorf));

		townGraphManager.addRoad(rockville, clinton, 30, "Wow");
		assertEquals("Wow", townGraphManager.getRoad(rockville, clinton));

		townGraphManager.addRoad(clinton, silverSpring, 20, "Nope");
		assertNotEquals("Not Nope", townGraphManager.getRoad(clinton, silverSpring));
	}
	
	@Test
	public void testGetRoad() {
		assertEquals("Shady Grove Road", townGraphManager.getRoad(gaithersburg, rockville));
		assertEquals("ICC 200", townGraphManager.getRoad(gaithersburg, silverSpring));
		assertEquals("ICC 201", townGraphManager.getRoad(gaithersburg, burtonsville));
	}

	@Test
	public void testAddTown() {
		String lolTown = "LOLTOWN";
		String ps5Town = "SCREW PS5 SCALPERS";

		townGraphManager.addTown(lolTown);
		assertTrue(townGraphManager.containsTown(lolTown));

		assertFalse(townGraphManager.containsTown(ps5Town));

		townGraphManager.addTown(ps5Town);
		assertTrue(townGraphManager.containsTown(ps5Town));
	}

	@Test
	public void testGetTown() {
		assertNotNull(townGraphManager.getTown(silverSpring));
		assertNotNull(townGraphManager.getTown(rockville));
		assertNotNull(townGraphManager.getTown(gaithersburg));
		assertNotNull(townGraphManager.getTown(waldorf));
		assertNull(townGraphManager.getTown("Calverton"));
	}

	@Test
	public void testContainsTown() {
		assertTrue(townGraphManager.containsTown(gaithersburg));
		assertTrue(townGraphManager.containsTown(clinton));
		assertTrue(townGraphManager.containsTown(ashton));

		assertFalse(townGraphManager.containsTown("Laurel"));
	}

	
	
}
