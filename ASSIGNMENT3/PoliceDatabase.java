


import java.util.Date;

public class PoliceDatabase {
    public static final int DRIVERSIZE = 2000;
    public static final int VEHICLES = 1000;
    public static final int INFRACTIONS = 800;

    Vehicle[] vehicles = null;
    public int numVehicles = 0;

    Driver[] drivers = null;
    public int numDrivers = 0;

    Infraction[] infractions = null;
    public int numInfraction = 0;

    public PoliceDatabase(){
        vehicles = new Vehicle[VEHICLES];
        drivers = new Driver[DRIVERSIZE];
        infractions = new Infraction[INFRACTIONS];
    }

    public void registerDriver(Driver aDriver){
        if(this.numDrivers==VEHICLES-1)
            System.out.println("Full, cannot add more drivers");
        else{
            for (int i = numDrivers; i < this.drivers.length; i++) {
                if (this.drivers[i] == null) {
                    this.drivers[i] = aDriver;
                    numDrivers = i + 1;
                    break;
                }
            }
//            drivers[this.numDrivers]=aDriver;
//            this.numDrivers++;
        }
    }
    public void registerVehicle(Vehicle aVehicle, String license){
        //判定
//        for (int i = 0; i < numDrivers; i++){
//            if (drivers[i].license.equals(license)){
//                vehicles[numDrivers] = aVehicle;
//                numVehicles++;
//            }
//        }
        if (numVehicles == VEHICLES-1){
            System.out.println("SPACE IS FULL");
        }
        else {
            vehicles[numVehicles]=aVehicle;
            for (int j =0; j < numDrivers; j++){
                if (drivers[j].license.equals(license)){
                    vehicles[numVehicles].owner = drivers[j];
                }
            }
            numVehicles++;

        }


    }
    public void unregisterVehicle(String plateNum){
        int a = -1;
        for (int i = 0 ; i< numVehicles; i++){
            if (vehicles[i].plate.equals(plateNum)){
                a = i;
            }

        }
        if (a != -1){
            for (int z = a; z < numVehicles-1;z++ ){
                vehicles[z] = vehicles[z+1];
            }
        }
        else {
            System.out.println("NO RESULTS FOUND IN THE SYSTEM");
        }

        numVehicles -=1;
    }
    //ask this, no value for stolen method in vechile
    public void changeOwner(String plate, String license) {
        Driver tempDriver = null;
        int temp = -1;
        for (int i = 0; i < numDrivers; i++){
            if (drivers[i].license.equals(license)){
                temp = i;
                tempDriver = drivers[i];
            }
        }
        for (int j = 0; j < numVehicles; j++){
            if (vehicles[j].plate.equals(plate)){
                vehicles[j].owner = tempDriver;
            }
        }
    }
    public void reportStolen(String plate){
        for (int i = 0; i < numVehicles; i++){
            if (vehicles[i].plate.equals(plate)){
                vehicles[i].reportedStolen = true;
                break;
            }
        }

    }
    public Infraction issueInfraction(String license, float amount, String description, Date date){
        Infraction rest = null;
        if (numInfraction < INFRACTIONS){
            for(int i =0; i< numDrivers;i++){
                if (drivers[i].license.equals(license)){
                    infractions[numInfraction]= new Infraction(amount,description,date);
                    infractions[numInfraction].driver = drivers[i];
                    rest = infractions[numInfraction];
                    numInfraction++;
                    break;
                }
            }

        }
        else {
            System.out.println("INFRACTIONS ARE FULL");
            rest = null;
        }
        return rest;
    }

    public boolean hasOutstandingInfractions(Driver d){
        boolean result=false;
        for (int i =0; i < numInfraction; i ++) {
            if(infractions[i].driver.equals(d) == true && infractions[i].outstanding == false) {
                result = true;
            }
        }
        return result;
    }


    public boolean shouldStopVehicle(String plate){
        boolean result = false;
        for (int i =0; i< numVehicles; i++){
            if(vehicles[i].plate.equals(plate)){
                if(hasOutstandingInfractions(vehicles[i].owner)||vehicles[i].reportedStolen){
                    result = true;
                }
                break;
            }
        }
        return result;
    }
    public void showInfractionsFor(String license){
        int counter = 0;
        for(int i =0; i<numInfraction;i++){
            if (infractions[i].driver.license.equals(license)){
                if (infractions[i].outstanding == false)
                    counter+=1;
                System.out.println(infractions[i]);
            }
        }
        System.out.println("Total outstanding infraction = "+counter);

    }
    public Driver[] cleanDrivers(){
        int counter = 0;
        int[] temp = new int[numDrivers];
        for(int i =0; i< numInfraction;i++){
            for(int j = 0; j < numDrivers; j++){
                if(drivers[j]==infractions[i].driver){
                    if(temp[j]==0){
                        temp[j]=1;
                        counter++;
                    }
                }
            }
        }
        Driver[] result= new Driver[numDrivers-counter];
        int j = 0;
        for(int i = 0; i<temp.length;i++){
            if(temp[i]==0){
                result[j] = drivers[i];
                j++;
            }
        }
        return result;
    }

    public void showInfractionReport() {
        int[] temp = new int[numDrivers];
        float[] money = new float[numDrivers];
        for(int i =0; i< numInfraction;i++){
            for(int j = 0; j < numDrivers; j++){
                if(drivers[j]==infractions[i].driver){
                    temp[j]++;
                    if(infractions[i].outstanding == true){
                        money[j]+=infractions[i].amount;
                    }
                }
            }
        }
        for(int i = 0; i<numDrivers; i++){
            if(temp[i] > 0)
                System.out.println(String.format("%20s", drivers[i].name)+": "+temp[i]+" infractions, total paid = $"+money[i]);
        }
    }



    public static PoliceDatabase example() { // Register all drivers and their vehicles
        PoliceDatabase pdb = new PoliceDatabase();
        pdb.registerDriver(new Driver("L1567-34323-84980", "Matt Adore",
                "1323 Kenaston St.", "Gloucester", "ON"));
        pdb.registerDriver(new Driver("L0453-65433-87655", "Bob B. Pins",
                "32 Rideau Rd.", "Greely", "ON"));
        pdb.registerDriver(new Driver("L2333-45645-54354", "Stan Dupp",
                "1355 Louis Lane", "Gloucester", "ON"));
        pdb.registerDriver(new Driver("L1234-35489-99837", "Ben Dover",
                "2348 Walkley Rd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L8192-87498-27387", "Patty O'Lantern",
                "2338 Carling Ave.", "Nepean", "ON"));
        pdb.registerDriver(new Driver("L2325-45789-35647", "Ilene Dover",
                "287 Bank St.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1213-92475-03984", "Patty O'Furniture",
                "200 St. Laurant Blvd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1948-87265-34782", "Jen Tull",
                "1654 Stonehenge Cres.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L0678-67825-83940", "Jim Class",
                "98 Oak Blvd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L0122-43643-73286", "Mark Mywords",
                "3 Third St.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L6987-34532-43334", "Bob Upandown",
                "434 Gatineau Way", "Hull", "QC"));
        pdb.registerDriver(new Driver("L3345-32390-23789", "Carrie Meehome",
                "123 Thurston Drive", "Kanata", "ON"));
        pdb.registerDriver(new Driver("L3545-45396-88983", "Sam Pull",
                "22 Colonel By Drive", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1144-26783-58390", "Neil Down",
                "17 Murray St.", "Nepean", "ON"));
        pdb.registerDriver(new Driver("L5487-16576-38426", "Pete Reedish",
                "3445 Bronson Ave.", "Ottawa", "ON"));
        pdb.registerVehicle(new Vehicle("Honda", "Civic", 2015, "yellow", "W3EW4T"),
                "L0453-65433-87655");
        pdb.registerVehicle(new Vehicle("Pontiac","Grand Prix",2007,"dark green","GO SENS"),
                "L0453-65433-87655");
        pdb.registerVehicle(new Vehicle("Mazda", "RX-8", 2004, "white", "OH YEAH"),
                "L2333-45645-54354");
        pdb.registerVehicle(new Vehicle("Nissan","Altima",2017,"bergundy", "Y6P9O7"),
                "L1234-35489-99837");
        pdb.registerVehicle(new Vehicle("Saturn", "Vue", 2002, "white", "9R6P2P"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Honda", "Accord", 2018, "gray", "7U3H5E"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Chrysler", "PT-Cruiser", 2006, "gold", "OLDIE"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Nissan", "Cube", 2010, "white", "5Y6K8V"),
                "L1948-87265-34782");
        pdb.registerVehicle(new Vehicle("Porsche", "959", 1989, "silver", "CATCHME"),
                "L0678-67825-83940");
        pdb.registerVehicle(new Vehicle("Kia", "Soul", 2018, "red", "J8JG2Z"),
                "L0122-43643-73286");
        pdb.registerVehicle(new Vehicle("Porsche", "Cayenne", 2014, "black", "EXPNSV"),
                "L6987-34532-43334");
        pdb.registerVehicle(new Vehicle("Nissan", "Murano", 2010, "silver", "Q2WF6H"),
                "L3345-32390-23789");
        pdb.registerVehicle(new Vehicle("Honda", "Element", 2008, "black", "N7MB5C"),
                "L3545-45396-88983");
        pdb.registerVehicle(new Vehicle("Toyota", "RAV-4", 2010, "green", "R3W5Y7"),
                "L3545-45396-88983");
        pdb.registerVehicle(new Vehicle("Toyota", "Celica", 2006, "red", "FUNFUN"),
                "L5487-16576-38426");

        return pdb;
    }

}


