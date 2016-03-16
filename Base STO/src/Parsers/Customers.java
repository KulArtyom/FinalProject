package Parsers;


import ui.Constans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by KulArtyom on 29.02.2016.
 */
public class Customers {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constans.DATE_FORMAT);

    private long id;
    private String name;
    private String surname;
    private String middle_name;
    private Date lastOrder;
    private Date dateOfBirth;
    private ArrayList<String> car;
    private boolean discount;


    public Customers() {
        this.car = new ArrayList<>();
    }

    public Customers(long id, SimpleDateFormat simpleDateFormat, String name, String surname, String middle_name, Date lastOrder, Date dateOfBirth, ArrayList<String> car, boolean discount) {
        this.id = id;
        this.simpleDateFormat = simpleDateFormat;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
        this.lastOrder = lastOrder;
        this.dateOfBirth = dateOfBirth;
        this.car = car;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(Date lastOrder) {
        this.lastOrder = lastOrder;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList getCar() {
        return car;
    }

    public void setCar(ArrayList<String> car) {
        this.car = car;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customers customers = (Customers) o;

        if (id != customers.id) return false;
        if (discount != customers.discount) return false;
        if (simpleDateFormat != null ? !simpleDateFormat.equals(customers.simpleDateFormat) : customers.simpleDateFormat != null)
            return false;
        if (name != null ? !name.equals(customers.name) : customers.name != null) return false;
        if (surname != null ? !surname.equals(customers.surname) : customers.surname != null) return false;
        if (middle_name != null ? !middle_name.equals(customers.middle_name) : customers.middle_name != null)
            return false;
        if (lastOrder != null ? !lastOrder.equals(customers.lastOrder) : customers.lastOrder != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(customers.dateOfBirth) : customers.dateOfBirth != null)
            return false;
        return car != null ? car.equals(customers.car) : customers.car == null;

    }

    @Override
    public int hashCode() {
        int result = simpleDateFormat != null ? simpleDateFormat.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (middle_name != null ? middle_name.hashCode() : 0);
        result = 31 * result + (lastOrder != null ? lastOrder.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (discount ? 1 : 0);
        return result;
    }


}
