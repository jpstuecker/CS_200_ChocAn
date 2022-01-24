package database;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author Rollins Baird
 */
public class ServiceRecordTest {
	
	Date dateTime = new Date();
    Date dateProvided = new Date();
    ServiceRecord newRecord;
    	
	@Before
	public void setUp() throws Exception {
		newRecord = new ServiceRecord(dateTime, dateProvided, 1, 10, 999, "Lot's of chocolate", 3.99);
	}
	
	@Test
	public void testGetDateTime() throws Exception {
		Date check = newRecord.getDateTime();
		assertEquals(check, dateTime);
	}

	@Test
	public void testGetDateProvided() throws Exception {
		Date check = newRecord.getDateProvided();
		assertEquals(check, dateProvided);
	}
	
	@Test
	public void testGetProviderNumber() throws Exception {
		int check = newRecord.getProviderNumber();
		assertEquals(check, 1);
	}
	
	@Test
	public void testGetMemberNumber() throws Exception {
		int check = newRecord.getMemberNumber();
		assertEquals(check, 10);
	}
	
	@Test
	public void testGetServiceCode() throws Exception {
		int check = newRecord.getServiceCode();
		assertEquals(check, 999);
	}
}
