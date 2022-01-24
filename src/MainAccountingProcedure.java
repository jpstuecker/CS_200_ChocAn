/**
 * Main Accounting Procedure calls for the generation of 4 different reports
 * @author Nate Purcell
 */
public class MainAccountingProcedure {
	public static boolean runReports() {
		GenerateReport.generateReport("SummaryReport");
		GenerateReport.generateReport("EFTReport");
		GenerateReport.generateReport("ProviderReport");
		GenerateReport.generateReport("MemberReport");
		return true;
	}
}
