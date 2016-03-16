package Parsers;

import Exception.MyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ui.Constans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserGson implements ParseInterface {

    @Override
    public Root parse(String filePath) throws Exception {

        if (filePath == null) {
            throw new MyException(Constans.TEXT_ERROR_GSON_NOT_FOUND);
        }

        Pattern pattern = Pattern.compile(Constans.REGEX_JSON);
        Matcher matcher = pattern.matcher(filePath);

        if (!matcher.find()) {
            throw new MyException(Constans.TEXT_ERROR_GSON_NOT_FORMAT);
        }

        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);
        Gson gson = new GsonBuilder().setDateFormat(Constans.DATE_FORMAT).create();
        Root root = gson.fromJson(reader, Root.class);
        fileReader.close();

        return root;
    }

}
