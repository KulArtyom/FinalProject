package Parsers;


import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exception.MyException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import ui.Constans;

public class ParserJSON implements ParseInterface {

	@SuppressWarnings("unchecked")
	@Override
	public Root parse(String filePath) throws  IOException,
								ParseException, java.text.ParseException, MyException {

		if(filePath == null) {

			throw new MyException(Constans.TEXT_ERROR_JSON_NOT_FOUND);
		}

		Pattern pattern = Pattern.compile(Constans.REGEX_JSON);
		Matcher matcher = pattern.matcher(filePath);

		if(!matcher.find()) {
			throw new MyException(Constans.TEXT_ERROR_JSON_NOT_FORMAT);
		}

		Root root = new Root();

		Customers customers;
		JSONParser parser = new JSONParser();

		FileReader fileReader = new FileReader(filePath);

		JSONObject object = (JSONObject) parser.parse(fileReader);

		root.setName((String) object.get(Constans.ELEMENT_NAME));
		root.setLocation((String) object.get(Constans.ELEMENT_LOCATION));
		JSONArray jsonarray = (JSONArray) object.get(Constans.ELEMENT_CUSTOMERS);

		for(int i = 0; i < jsonarray.size(); i++) {

			customers = new Customers();

			JSONObject jsonObject = (JSONObject) jsonarray.get(i);
			customers.setId((long) jsonObject.get(Constans.ELEMENT_ID));
			customers.setName((String) jsonObject.get(Constans.ELEMENT_CUSTOMERS_NAME));
			customers.setSurname((String) jsonObject.get(Constans.ELEMENT_CUSTOMERS_SURNAME));
			customers.setMiddle_name((String) jsonObject.get(Constans.ELEMENT_CUSTOMERS_MIDDLE_NAME));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constans.DATE_FORMAT);
			customers.setLastOrder(simpleDateFormat.parse((String) jsonObject.get(Constans.ELEMENT_CUSTOMERS_LAST_ORDER)));
			customers.setDateOfBirth(simpleDateFormat.parse((String) jsonObject.get(Constans.ELEMENT_CUSTOMERS_DATE_OF_BIRTH)));
			JSONArray cararray = (JSONArray) jsonObject.get(Constans.ELEMENT_CUSTOMERS_CAR);
			customers.setCar((ArrayList<String>) cararray);
			customers.setDiscount((boolean) jsonObject.get(Constans.ELEMENT_CUSTOMERS_DISCOUNT));

			root.getCustomers().add(customers);
		}

		fileReader.close();
		return root;
	}

}
