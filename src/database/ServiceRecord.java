package database;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class holds member data
 * @author  Rollins Baird
 */
@SuppressWarnings("serial")
public class ServiceRecord extends ServiceDatabase.Record{

    /***
     * Creates a ServiceRecord instance
     * @param dateTime          a Date object of the date & time
     * @param dateProvided      the date service was provided
     * @param providerNumber    the provider number
     * @param memberNumber      the member number
     * @param serviceCode       the service code
     * @param comments          the comments
     * @param fee               the service fee
     */
    public ServiceRecord(Date dateTime, Date dateProvided, int providerNumber, int memberNumber, int serviceCode, String comments, double fee){
        this.dateTime = dateTime;
        this.dateProvided = dateProvided;
        this.providerNumber = providerNumber;
        this.memberNumber = memberNumber;
        this.serviceCode = serviceCode;
        this.comments = comments;
        this.fee = fee;
    }
    public ServiceRecord(String dateTime, String dateProvided, int providerNumber, int memberNumber, int serviceCode, String comments, double fee) throws ParseException {
    	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    	Date date = formatter.parse(dateTime);
    	Date providedDate = formatter.parse(dateProvided);
    	this.dateTime = date;
    	this.dateProvided = providedDate;
    	this.providerNumber = providerNumber;
    	this.memberNumber = memberNumber;
    	this.serviceCode = serviceCode;
    	this.comments = comments;
    	this.fee = fee;   	
    }

    public int getServiceNumber() {
        return this.getServiceNumber();
    }
}