package database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
* @author Rollins Baird
*/
public class MemberTest {
	
	String name = "Rollins";
    String status = "VALID";
    String street = "123 Main St";
    String city = "Witchata";
    String zip = "12345";
    String state = "KS";
    Member newMember;

	@Before
	public void setUp() throws Exception {
		newMember = new Member(name, status, street, city, zip, state);
	}

	@Test
	public void testGetName() {
		String check = newMember.getName();
		assertEquals(name, check);
	}
	
	@Test
	public void testGetStatus() {
		String check = newMember.getStatus();
		assertEquals(status, check);
	}
	
	@Test
	public void testGetStreet() {
		String check = newMember.getStreet();
		assertEquals(street, check);
	}
	
	@Test
	public void testGetCity() {
		String check = newMember.getCity();
		assertEquals(city, check);
	}
	
	@Test
	public void testGetZip() {
		String check = newMember.getZip();
		assertEquals(zip, check);
	}
	
	@Test
	public void testGetState() {
		String check = newMember.getState();
		assertEquals(state, check);
	}
}
