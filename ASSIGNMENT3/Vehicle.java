public class Vehicle {
    public String make;
    public String model;
    public int year;
    public String color;
    public String plate;
    Driver owner = null;
    public Boolean reportedStolen =false;

    public Vehicle(){
        make = null;
        model = null;
        year = 0;
        color = null;
        plate = null;
    }
    public Vehicle(String make, String model, int year, String color, String plate){
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.plate = plate;

    }
    public String toString(){
        return "A "+ color+" "+year+" "+make+" "+model+" "+ "with plate "+plate;
    }

}
