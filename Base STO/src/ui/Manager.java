package ui;

import Connection.Connection;
import Exception.MyException;
import Parsers.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by KulArtyom on 02.03.2016.
 */
public class Manager {


    Connection connection = Connection.getInstance();

    //Реализация паттерна Singlton
    private final static Manager instance = new Manager();

    private Manager() {
    }

    public static Manager getInstance() {
        return instance;
    }

    private Root root;

    public Root getRoot() {
        return root;
    }

    public String getLINK_XML() {
        return Constans.LINK_XML;
    }

    public String getLINK_JSON() {
        return Constans.LINK_JSON;
    }

    public String getSAX() {
        return Constans.SAX;
    }

    public String getJSON() {
        return Constans.JSON;
    }

    public String getGson() {
        return Constans.GSON;
    }

    // Мелод возращает путь файла XML
    public String getFilePathXML() {
        return connection.getFILE_XML();
    }

    // Мелод возращает путь файла JSON
    public String getFilePathJSON() {

        return connection.getFILE_JSON();
    }

    // Метод записывает ссылку на путь файла
    public void setFilePath(String filePath) {
        connection.setFilePath(filePath);
    }


    //Метод скачивает файл
    public void downloadFiles(String link, String filePath) throws MyException, IOException {
        File file = new File(filePath);
        if (!file.exists())
            connection.downloadFile(link);

    }

    //Метод удаляет файл
    public void deleteFiles(String filePath) throws MyException {
        connection.deleteFile(filePath);
    }

    // Метод выбора парсера
    public void setChoiceParse(String lib) throws Exception {

        if (lib.equals(getSAX())) {
            this.root = new ParserSAX().parse(connection.getFilePath());
        } else if (lib.equals(getJSON())) {
            this.root = new ParserJSON().parse(connection.getFilePath());
        } else if (lib.equals(getGson())) {
            this.root = new ParserGson().parse(connection.getFilePath());
        } else {
            throw new MyException("Manager error no such parser");
        }
    }

    // Метод очищает объект класса Root
    public void cleanRoot() {
        if (this.root != null) {
            this.root = null;
        }
    }

    // Метод выводит в консоль клиентов
    public void showCustomers(ArrayList<Customers> customers) {

        if (customers == null) {
            System.out.println("Customers = null");
            return;
        }

        for (Customers customer : customers) {

            System.out.println("============================");
            System.out.println("id: " + customer.getId());
            System.out.println("name: " + customer.getName());
            System.out.println("surname: " + customer.getSurname());
            System.out.println("middle_name: " + customer.getMiddle_name());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constans.DATE_FORMAT);
            System.out.println("lastOrder: " + simpleDateFormat.format(customer.getLastOrder()));
            System.out.println("dateOfBirth: " + simpleDateFormat.format(customer.getDateOfBirth()));
            System.out.println("discount: " + customer.isDiscount());


            for (int j = 0; j < customer.getCar().size(); j++) {
                System.out.println("car: " + customer.getCar().get(j));
            }

        }
    }

    // Метод выводит в консоль объект Root
    public void showRoot(Root root) {

        System.out.println("Name: " + root.getName());
        System.out.println("Location: " + root.getLocation());
        showCustomers(root.getCustomers());

    }

    //Метод производит поиск клиентов по ФИО
    public ArrayList<Customers> searchCustomers() throws MyException {

        if (this.root == null) {
            throw new MyException("Root = null");
        }
        System.out.println("Для поиска клиента по ФИО");
        System.out.println("Введите Фамилию клиента: ");
        Scanner scanner = new Scanner(System.in);
        String sName = scanner.next();
        System.out.println("Введите Имя: ");
        String sSurname = scanner.next();
        System.out.println("Введите Отчество: ");
        String sMiddle_name = scanner.next();


        ArrayList<Customers> customers = null;


        for (Customers custom : root.getCustomers()) {
            if (sName.equals(custom.getName()) || sSurname.equals(custom.getSurname()) || sMiddle_name.equals(custom.getMiddle_name())) {
                if (customers == null) {
                    customers = new ArrayList<>();
                }
                customers.add(custom);
            }
        }

        if (customers != null) {
            System.out.println("Найден:");
            return customers;
        }else  {
            System.out.println("Не найден: " + sName + " " + sSurname + " " + sMiddle_name);
        }
        return null;


    }


    //Метод сортирует клиентов ккоторые обращались на СТО 3 месяца назад и позже
    public ArrayList<Customers> searchDate() throws MyException {

        if (this.root == null) {
            throw new MyException("Root = null");
        }

        ArrayList<Customers> customers = null;

        for (Customers custom : root.getCustomers()) {
            GregorianCalendar calendarlastOrder = new GregorianCalendar();
            calendarlastOrder.setTime(custom.getLastOrder());
            calendarlastOrder.add(Calendar.MONTH, -1);

            GregorianCalendar month6 = new GregorianCalendar();
            month6.add(Calendar.MONTH, -7);

            if (calendarlastOrder.get(GregorianCalendar.MONTH) <= month6.get(GregorianCalendar.MONTH)) {
                if (customers == null) {
                    customers = new ArrayList<>();
                }
                customers.add(custom);
            }
        }

        if (customers != null) {
            return customers;

        }
        return null;

    }

    //Метод производит поиск по марке авто
    public ArrayList<Customers> searchAuto() throws MyException {

        if (this.root == null) {
            throw new MyException("Root = null");
        }

        ArrayList<Customers> customers = null;

        System.out.println("Для поиска клиента по марке авто");
        System.out.println("Введите название марки авто: ");
        Scanner scanner = new Scanner(System.in);
        String sAuto = scanner.next();


        for (Customers custom : root.getCustomers()) {
            if (custom.getCar().contains(sAuto)) {
                if (customers == null) {
                    customers = new ArrayList<>();
                }
                customers.add(custom);
            }
        }
        if (customers != null) {
            return customers;
        }
        return null;
    }

    //Метод производит сортировку клиентов с днем рождения в этом месяце
    public ArrayList<Customers> searchBirthDay() throws MyException

    {
        if (this.root == null) {
            throw new MyException("Root = null");
        }

        ArrayList<Customers> customers = null;

        for (Customers custom : root.getCustomers()) {

            GregorianCalendar calendarBirthDay = new GregorianCalendar();
            calendarBirthDay.setTime(custom.getDateOfBirth());
            calendarBirthDay.add(Calendar.MONTH, -1);

            GregorianCalendar month = new GregorianCalendar();
            month.add(Calendar.MONTH, -1);

            if (calendarBirthDay.get(GregorianCalendar.MONTH) == month.get(GregorianCalendar.MONTH)) {
                if (customers == null) {
                    customers = new ArrayList<>();
                }
                customers.add(custom);
            }
        }

        if (customers != null) {
            return customers;

        }
        return null;
    }
}
