import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import database.Member;
import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;
/**
 * @author Matt Poirier
 */
public class GenerateReportTest {
	
	Member m1;
	
	@Before
	public void setUp() throws Exception {
		m1 = new Member("Joe", "SUSPENDED", "1831 University Blvd", "Tuscaloosa", "AL", "35404");
	}

	@Test
	public void testMemberReport() {
		boolean success = GenerateReport.generateReport("MemberReport");
		assertTrue(success);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBadReport() {
		GenerateReport.generateReport("ServiceReport");
	}
	
	@Test
	public void testSummaryReport() {
		Report summaryReport = new SummaryReport();
		summaryReport.generateReport();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date today = new Date();
		File file = new File("Summary Report " + sdf.format(today) + ".txt");
		assertTrue(file.exists());
	}
	
	@Test
	public void testMemberGetName() {
		assertEquals(m1.getName(),"Joe");
	}
	
	@Test
	public void testMemberGetStreet() {
		assertEquals(m1.getStreet(),"1831 University Blvd");
	}

}
