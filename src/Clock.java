
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
/**
 * Clock that tells the main accounting procedure to run at midnight on Friday
 * @author Nate Purcell
 */
public class Clock  {
	public static void main() {
		while(true) {
			//gets current time
			LocalTime time = LocalTime.now();
			//gets current date
			LocalDate date = LocalDate.now();
			//converts date class to a string for comparison
			String day = (date.getDayOfWeek()).toString();
			//checks if friday
			if (day == "FRIDAY") {
				//checks if midnight
				if (time.getHour() == 0) {
					MainAccountingProcedure.runReports();
					//wait an hour before rechecking
					try {
						TimeUnit.HOURS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else
					//wait a minute before rechecking
					try {
						TimeUnit.MINUTES.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			 
			//wait a minute before rechecking
			try {
				TimeUnit.MINUTES.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
