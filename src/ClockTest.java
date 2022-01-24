import static org.junit.Assert.*;
import java.time.LocalTime;
import java.time.LocalDate;

import org.junit.Test;
/**
 * 
 * @author Nate Purcell
 *
 */
public class ClockTest {
	LocalTime time;
	LocalDate date;	
	@Test
	public void testTime() {
		assertNotSame(time, "00:00.00.000");
	}
	@Test
	public void testDate() {
		assertNotSame(date, "FRIDAY");
	}
}
