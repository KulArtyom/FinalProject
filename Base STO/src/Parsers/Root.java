package Parsers;


import java.util.ArrayList;

public class Root {


    private String name;
    private String location;
    private ArrayList<Customers> customers;

    public Root() {
        customers = new ArrayList<>();
    }

    public Root(String name, String location, ArrayList<Customers> customers) {
        this.name = name;
        this.location = location;
        this.customers = customers;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    protected void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Customers> getCustomers() {
        return customers;
    }

    protected void setCustomers(ArrayList<Customers> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Root{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", customers=" + customers +
                '}';
    }
}
