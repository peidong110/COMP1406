public class Driver {
    public String license;
    public String name;
    public String street;
    public String city;
    public String province;
    public Driver(){
        license = null;
        name = null;
        street = null;
        city = null;
        province = null;

    }
    public Driver(String license, String name, String street, String city, String province){
        this.license = license;
        this.name = name;
        this.street = street;
        this.city = city;
        this.province = province;
    }
    public String toString(){
        return "#"+license +" "+ name + " living at "+ street +","+ city +", "+province;
    }


}
