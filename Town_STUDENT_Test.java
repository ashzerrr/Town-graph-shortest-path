
package Pj6;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {

	Town gaithersburg;
	Town rockville;
	Town silverSpring1;
	Town silverSpring2;

	@Before
	public void setUp() throws Exception {
		gaithersburg = new Town("Gaithersburg");
		rockville = new Town("Rockville");
		silverSpring1 = new Town("Silver Spring");
		silverSpring2 = new Town("Silver Spring");
	}

	@After
	public void tearDown() throws Exception {
		gaithersburg = rockville = silverSpring1 = silverSpring2 = null;
	}

	@Test
	public void getNameTest() {
		assertEquals("Gaithersburg", gaithersburg.getName());
		assertEquals("Rockville", rockville.getName());
		assertEquals("Silver Spring", silverSpring1.getName());
	}

	@Test
	public void createCopyTest() {
		Town gaithersburgCopy = new Town(gaithersburg);
		assertEquals(gaithersburg, gaithersburgCopy);

		Town silverSpringCopy = new Town(silverSpring1);
		assertEquals(silverSpring1, silverSpringCopy);
	}

	@Test
	public void equalsTest() {
		Town germantown = new Town("Germantown");
		assertNotEquals(rockville, germantown);

		assertEquals(silverSpring1, silverSpring2);

		Town gaithersburgCopy = new Town("Gaithersburg");
		assertEquals(gaithersburg, gaithersburgCopy);
	}

	@Test
	public void hashCodeTest() {
		Town germantown = new Town("Germantown");
		assertNotEquals(germantown.hashCode(), gaithersburg.hashCode());

		assertEquals(silverSpring1.hashCode(), silverSpring2.hashCode());

		assertNotEquals(rockville.hashCode(), silverSpring1.hashCode());
	}

	@Test
	public void compareToTest() {
		assertTrue(gaithersburg.compareTo(rockville) <= 0);
		assertTrue(silverSpring1.compareTo(silverSpring2) == 0);
		assertTrue(rockville.compareTo(silverSpring1) <= 0);
	}
}
