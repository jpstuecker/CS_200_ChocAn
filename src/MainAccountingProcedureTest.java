import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Nate Purcell
 *
 */
public class MainAccountingProcedureTest {

	@Test
	public void testRunReports() {
		assertTrue(MainAccountingProcedure.runReports());
	}

}
