import database.*;
import database.ServiceDatabase.Record;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
/*
 * generates report for provider directory in alphabetical order
 * author @Joseph Ruzicka
 */
public class ProviderDirectoryReport extends Report {
    int count = 0;
    /**
     * Generates report of alphabetized list of services along with their respective service code and fees
     */
    public void generateReport() {
        ArrayList<ProviderDirectory> ProviderDirectory = Main.providerDirectory.getEntries();
        ArrayList<String> AlphProvDirectory = new ArrayList<String>();
        ArrayList<ProviderDirectory> ReStore = new ArrayList<ProviderDirectory>();
        for(int i =0; i < ProviderDirectory.size(); i++)
        {
            AlphProvDirectory.add(ProviderDirectory.get(i).getName());
            
        }
        Collections.sort(AlphProvDirectory);
        int count = 0;
        for(int i = 0; i < ProviderDirectory.size(); i++)
        {
            while (AlphProvDirectory.get(i) != ProviderDirectory.get(count).getName())
            {
                count++;
            }
            ProviderDirectory adder = new ProviderDirectory(AlphProvDirectory.get(i), ProviderDirectory.get(count).getServiceFee());
            ReStore.add(adder);
            count = 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date currentDate = new Date();
        try {
        // open file
        
        FileWriter providerDirectoryReport = new FileWriter("ProviderDirectory" + " " + sdf.format(currentDate) + ".txt");
        // write provider directory info into file
        
        for(int i =0; i < ReStore.size(); i++)
        {
        providerDirectoryReport.write("Name: " + ReStore.get(i).getName() +  "\nService Code: " + ReStore.get(i).printServiceCode() + "\nService Fee: " + ReStore.get(i).getServiceFee() +"\n\n");
        }
         providerDirectoryReport.close();
        }
         catch(Exception e) {
             System.out.println("Error accessing provider directory");
         }
      
        }
    }

