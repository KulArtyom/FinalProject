package Exception;

/**
 * Created by KulArtyom on 10.03.2016.
 */
public class MyException extends Exception {

    public MyException(String msg) {
        super(msg);
    }

    public String getRussianMessage() {
        return super.getMessage();
    }
}
