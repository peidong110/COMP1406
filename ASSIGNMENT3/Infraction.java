import java.util.Date;
public class Infraction {
    public float amount;
    public String description;
    public Date dateIssued;
    public boolean outstanding =false;
    Driver driver= null;
    public Infraction(){
        amount = 0;
        description = null;
        dateIssued = null;

    }
    public Infraction(float amount, String description, Date dateIssued) {
        this.amount = amount;
        this.description = description;
        this.dateIssued = dateIssued;
    }
    public void pay(){
        outstanding = true;
    }


    //overload
    public String toString(){
        if (outstanding == true){
            return "$"+ amount + " Infraction on "+dateIssued+" "+"[PAID IN FULL]";

        }
        else {
            return "$"+ amount + " Infraction on "+dateIssued+" "+"[OUSTANDING]";

        }
    }

}


