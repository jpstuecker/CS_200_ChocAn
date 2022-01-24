/**
 * Report is an abstract class that each report type will inherit.
 * @author Matt Poirier
 *
 */
public abstract class Report {
	/**
	 * Class contructor.
	 */
	public Report() {
	}
	
	/**
	 * Will be implemented in each subclass and will gather all data necessary for report
	 */
	public abstract void generateReport();
	
	/**
	 * This would be implemented before the project went into a production environment
	 */
	public void printReport() {
		// this would be implemented before the project went into a production environment
	}
	
	/**
	 * This would be implemented before the project went into a production environment
	 */
	public void emailReport() {
		// this would be implemented before the project went into a production environment
	}
}
