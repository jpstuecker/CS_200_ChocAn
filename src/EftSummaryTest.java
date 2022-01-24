import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import database.Member;
import database.Provider;

public class EftSummaryTest {

	Member m1;
	Provider p1;
	
	@Before
	public void setUp() throws Exception {
		m1 = new Member("Travis", "VALID", "123 Example St", "Madison", "AL", "35758");
		p1 = new Provider("Provider Test", "456 Example Ave", "Dothan", "AL", "35423");
	}

	@Test
	public void testEftReport() {
		Report EftReport = new EftReport();
		EftReport.generateReport();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date today = new Date();
		File file = new File("Eft Report " + sdf.format(today) + ".txt");
		assertTrue(file.exists());
	}
	
	@Test
	public void testSummarySuccess() {
		SummaryReport SummaryReport = new SummaryReport();
		SummaryReport.generateReport();
		assertEquals(true, SummaryReport.getSuccess());
		
	}
	
	@Test
	public void testProviderReport() {
		Report ProviderReport = new ProviderReport();
		ProviderReport.generateReport();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date today = new Date();
		File file2 = new File("Provider Test " + sdf.format(today) + ".txt");
		assertTrue(file2.exists());
	}
	
	

}
