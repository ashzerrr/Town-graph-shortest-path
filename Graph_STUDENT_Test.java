package Pj6;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
	
	Graph graphOfTowns;

	Town townGaithersburg;
	Town townRockville;
	Town townSilverSpring;
	Town townClinton;
	Town townWaldorf;
	Town townAshton;
	Town townWhiteOak;
	Town townBurtonsville;

	@Before
	public void setUp() throws Exception {
	    graphOfTowns = new Graph();

	    townGaithersburg = new Town("Gaithersburg");
	    townRockville = new Town("Rockville");
	    townSilverSpring = new Town("Silver Spring");
	    townClinton = new Town("Clinton");
	    townWaldorf = new Town("Waldorf");
	    townAshton = new Town("Ashton");
	    townWhiteOak = new Town("White Oak");
	    townBurtonsville = new Town("Burtonsville");

	    graphOfTowns.addVertex(townGaithersburg);
	    graphOfTowns.addVertex(townRockville);
	    graphOfTowns.addVertex(townSilverSpring);
	    graphOfTowns.addVertex(townClinton);
	    graphOfTowns.addVertex(townWaldorf);
	    graphOfTowns.addVertex(townAshton);
	    graphOfTowns.addVertex(townWhiteOak);
	    graphOfTowns.addVertex(townBurtonsville);

	    graphOfTowns.addEdge(townGaithersburg, townRockville, 3, "Shady Grove Road");
	    graphOfTowns.addEdge(townGaithersburg, townSilverSpring, 8, "ICC 200");
	    graphOfTowns.addEdge(townRockville, townSilverSpring, 5, "Randolph Road");
	    graphOfTowns.addEdge(townSilverSpring, townClinton, 10, "I-495");
	    graphOfTowns.addEdge(townClinton, townWaldorf, 3, "Some road");
	    graphOfTowns.addEdge(townSilverSpring, townBurtonsville, 2, "Spencerville Road");
	    graphOfTowns.addEdge(townSilverSpring, townWhiteOak, 3, "New Hampshire Ave N");
	    graphOfTowns.addEdge(townSilverSpring, townAshton, 1, "New Hampshire Ave S");
	    graphOfTowns.addEdge(townGaithersburg, townBurtonsville, 16, "ICC 201");
	}

	@After
	public void tearDown() throws Exception {
		townGaithersburg = townRockville = townSilverSpring = townClinton = townWaldorf = townAshton = townWhiteOak = townBurtonsville = null;
		graphOfTowns = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(townGaithersburg, townRockville, 3, "Shady Grove Road"), graphOfTowns.getEdge(townGaithersburg, townRockville));
		assertEquals(new Road(townGaithersburg, townSilverSpring, 8, "ICC 200"), graphOfTowns.getEdge(townGaithersburg, townSilverSpring));
		assertEquals(new Road(townRockville, townSilverSpring, 5, "Randolph Road"), graphOfTowns.getEdge(townRockville, townSilverSpring));
		assertEquals(new Road(townSilverSpring, townClinton, 10, "I-495"), graphOfTowns.getEdge(townSilverSpring, townClinton));
		assertEquals(new Road(townClinton, townWaldorf, 3, "Some road"), graphOfTowns.getEdge(townClinton, townWaldorf));
		assertEquals(new Road(townSilverSpring, townBurtonsville, 2, "Spencerville Road"), graphOfTowns.getEdge(townSilverSpring, townBurtonsville));
		assertEquals(new Road(townSilverSpring, townWhiteOak, 3, "New Hampshire Ave N"), graphOfTowns.getEdge(townSilverSpring, townWhiteOak));
		assertEquals(new Road(townSilverSpring, townAshton, 1, "New Hampshire Ave S"), graphOfTowns.getEdge(townSilverSpring, townAshton));
		assertEquals(new Road(townGaithersburg, townBurtonsville, 16, "ICC 201"), graphOfTowns.getEdge(townGaithersburg, townBurtonsville));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graphOfTowns.containsEdge(townGaithersburg, townWaldorf));
		graphOfTowns.addEdge(townGaithersburg, townWaldorf, 3, "Some road");
		assertEquals(true, graphOfTowns.containsEdge(townGaithersburg, townWaldorf));
	}
}
	
