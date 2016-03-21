package ui;

/**
 * Created by KulArtyom on 02.03.2016.
 */
public class Constans {

    public static final String LINK_XML = "http://kiparo.ru/t/service_station.xml";
    public static final String LINK_JSON = "http://kiparo.ru/t/service_station.json";

    public static final String SAX = "SAX";
    public static final String JSON = "JSON";
    public static final String GSON = "GSON";

    public final static String FILE_PATH_XML = "service_station.xml";
    public final static String FILE_PATH_JSON = "service_station.json";
    public final static String FILE_PATH = "service_station";

    public final static String TEXT_ERROR_DOWNLOADING = "Connection downloadFile error";
    public final static String TEXT_ERROR_DOWNLOADING_RESPONSE = "Connection downloadFile error response";
    public final static String TEXT_ERROR_DOWNLOADING_UNKNOWNHOST = "Connection downloadFile error UnknownHostException";
    public final static String TEXT_ERROR_DOWNLOADING_IOEXCEPTION = "Connection downloadFile error IOException";
    public final static String TEXT_ERROR_DOWNLOADING_UNKNOW_ERROR = "Connection downloadFile error unknown";
    public final static String TEXT_ERROR_DELETE_FILE = "File\\";
    public final static String TEXT_ERROR_DELETE_NOT_FOUND = "\\don't find";
    public final static String TEXT_ERROR_DELETE = "Connection deleteFile error";
    public final static String TEXT_ERROR_PARSESAX_NOT_FOUND = "ParserSAX error file not found";
    public final static String TEXT_ERROR_PARSESAX_NOT_FORMAT = "ParserSAX error file not xml format";
    public final static String TEXT_ERROR_GSON_NOT_FOUND = "ParserGson error file not found";
    public final static String TEXT_ERROR_GSON_NOT_FORMAT = "ParserGson error file not xml format";
    public final static String TEXT_ERROR_JSON_NOT_FOUND = "ParserJson error file not found";
    public final static String TEXT_ERROR_JSON_NOT_FORMAT = "ParserJson error file not xml format";
    public final static String TEXT_ERROR_MYEXCEP = "MyException main error ";
    public final static String TEXT_ERROR_IOEXCEP = "IOException main error ";
    public final static String TEXT_ERROR_EXCEP = "IOException main error ";

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String REGEX_XML = "\\.(xml)";
    public final static String REGEX_JSON = "\\.(json)";
    public final static String REGEX_FILES = "\\.(xml|json)";

    public final static String ELEMENT_NAME = "name";
    public final static String ELEMENT_LOCATION = "location";
    public final static String ELEMENT_CUSTOMERS = "customers";
    public final static String ELEMENT_ID = "id";
    public final static String ELEMENT_CUSTOMERS_NAME = "name";
    public final static String ELEMENT_CUSTOMERS_SURNAME = "surname";
    public final static String ELEMENT_CUSTOMERS_MIDDLE_NAME = "middle_name";
    public final static String ELEMENT_CUSTOMERS_LAST_ORDER = "lastOrder";
    public final static String ELEMENT_CUSTOMERS_DATE_OF_BIRTH = "dateOfBirth";
    public final static String ELEMENT_CUSTOMERS_CAR = "car";
    public final static String ELEMENT_CUSTOMERS_DISCOUNT = "discount";

    public final static String MAIN_TEXT_N = "\n";
    public final static String MAIN_TEXT_ENTER_OPERATION = "Для того чтобы провести операцию нажмите: ";
    public final static String MAIN_TEXT_ENTER_WAIT_DOWNLOAD = "Подождите идет скачиваение";
    public final static String MAIN_TEXT_ENTER_T = ".";
    public final static String MAIN_TEXT_ENTER_SUCCESFULLY_DOWNLOADED = "\"Files successfully downloaded\\n\"";
    public final static String MAIN_TEXT_FILES_ALREADY_EXISTS = "Files already exists";
    public final static String MAIN_TEXT_FILES_OWERWRITE = "Files successfully overwrite";
    public final static String MAIN_TEXT_FILES_DONT_FIND = "Files don't find\n";
    public final static String MAIN_TEXT_FILES_SUCCESFULLY_DELETED = "Files successfully deleted\n";
    public final static String MAIN_TEXT_FILES_SUCCESFULLY_PARSED = "File successfully parsed\n";
    public final static String MAIN_TEXT_SHOWS_ROOT = "Root successful shows\n";
    public final static String MAIN_TEXT_FIRST_DOWNLOAD = "First download and parse files";
    public final static String MAIN_TEXT_FIRST_ROOT_NULL = "Root = null";
    public final static String MAIN_TEXT_FIRST_ROOT_CLEAN = "Root successful clean\n";


}
