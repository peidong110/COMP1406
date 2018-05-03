import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class ExpiringPolicy extends Policy {
    private Date expiryDate;
    public ExpiringPolicy(float amount, Date date){
        super(amount);
        this.expiryDate = date;
    }
    public ExpiringPolicy(float amount){
        super(amount);
        GregorianCalendar aCalendar = new GregorianCalendar();
        aCalendar.add(Calendar.YEAR,1);
        expiryDate = aCalendar.getTime();

    }
    public boolean isExpired() {
        Date localTime = new Date();

        if (localTime.before(expiryDate) == false)
            return true;
        else
            return false;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public String toString(){
        Date localTime = new Date();
        SimpleDateFormat dateFormater = new SimpleDateFormat("MMMM dd, yyyy (hh:mma)");
        String formatTime = dateFormater.format(expiryDate);
        if (localTime.before(expiryDate) == false)
            return String.format("ExpiringPolicy: "+super.toString().substring(8)+" expired "+formatTime);
        else
            return String.format("ExpiringPolicy: "+super.toString().substring(8)+" expires "+formatTime);

    }
    public float handleClaim() {
        if (isExpired())
            return 0;
        else
            return super.amount;

    }
}
