import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import database.ProviderDirectory;
/**
 * Test for report functionality
 * @author Joseph Ruzicka
 *
 */
public class ProviderDirectoryReportTest {
    ProviderDirectory ProD;
    @Before
    /**
     * Constructor
     * @throws Exception
     */
    public void setUp() throws Exception {
        ProD = new ProviderDirectory("Aerobics", 28.0);
    }
    ProviderDirectory ProD2 = new ProviderDirectory("Zebra run", 37.0);
    /**
     * Adding entries to database and generating a report
     */
    @Test
    public void testGenerateReport() {
        Main.providerDirectory.addEntry(ProD);
        Main.providerDirectory.addEntry(ProD2);
        CreateProviderDirectory.directoryReport();
    }
   
   /**
    * Removes an entry and generates another report
    */
    @Test
    public void testRemove()
    {
        Main.providerDirectory.removeEntry(ProD);
        CreateProviderDirectory.directoryReport();
    }
    
    /**
     * Clears all entries
     */
    
    public void testSanity()
    {
       Main.providerDirectory.clear();
    }

}
