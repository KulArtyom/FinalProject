package Parsers;

import Exception.MyException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ui.Constans;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserSAX extends DefaultHandler implements ParseInterface {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constans.DATE_FORMAT);
    private String thisElement = "";
    private Root root;
    private Customers customers;

    public ParserSAX() {
        this.root = new Root();
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (thisElement.equals(Constans.ELEMENT_NAME) && customers == null) {
            root.setName(new String(ch, start, length));
        }

        if (thisElement.equals(Constans.ELEMENT_LOCATION) && customers == null) {
            root.setLocation(new String(ch, start, length));
        }

        if (thisElement.equals(Constans.ELEMENT_CUSTOMERS) && customers == null) {
            customers = new Customers();
        }
        if (thisElement.equals(Constans.ELEMENT_ID) && customers != null) {
            customers.setId(new Long(new String(ch, start, length)));
        }
        if (thisElement.equals(Constans.ELEMENT_CUSTOMERS_NAME) && customers != null) {
            customers.setName(new String(ch, start, length));
        }
        if (thisElement.equals(Constans.ELEMENT_CUSTOMERS_SURNAME) && customers != null) {
            customers.setSurname(new String(ch, start, length));
        }
        if (thisElement.equals(Constans.ELEMENT_CUSTOMERS_MIDDLE_NAME) && customers != null) {
            customers.setMiddle_name(new String(ch, start, length));
        }
        if (thisElement.equals(Constans.ELEMENT_CUSTOMERS_LAST_ORDER) && customers != null) {
            try {
                customers.setLastOrder(simpleDateFormat.parse(new String(ch, start, length)));
            } catch (ParseException e) {
                throw new SAXException("ParserSAX ParseException " + e.toString());
            }
        }
        if (thisElement.equals(Constans.ELEMENT_CUSTOMERS_DATE_OF_BIRTH) && customers != null) {
            try {
                customers.setDateOfBirth(simpleDateFormat.parse(new String(ch, start, length)));
            } catch (ParseException e) {
                throw new SAXException("ParserSAX ParseException " + e.toString());
            }
        }
        if (thisElement.equals(Constans.ELEMENT_CUSTOMERS_CAR) && customers != null) {
            customers.getCar().add(new String(ch, start, length));
        }
        if (thisElement.equals(Constans.ELEMENT_CUSTOMERS_DISCOUNT) && customers != null) {
            customers.setDiscount(new Boolean(new String(ch, start, length)));

            root.getCustomers().add(customers);
            customers = null;
        }
    }

    @Override
    public Root parse(String filePath) throws SAXException, IOException, ParserConfigurationException, MyException {

        if (filePath == null) {
            throw new MyException(Constans.TEXT_ERROR_PARSESAX_NOT_FOUND);
        }

        Pattern pattern = Pattern.compile(Constans.REGEX_XML);
        Matcher matcher = pattern.matcher(filePath);

        if (!matcher.find()) {
            throw new MyException(Constans.TEXT_ERROR_PARSESAX_NOT_FORMAT);
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        ParserSAX parserSAX = new ParserSAX();
        parser.parse(new File(filePath), parserSAX);

        return parserSAX.root;
    }

}
