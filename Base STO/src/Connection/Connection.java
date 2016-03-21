package Connection;

import Exception.MyException;
import ui.Constans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Connection {

    private static final Connection instance = new Connection();
    private String filePath;

    //Реализация паттерна Singlton
    //Паттерн Singleton гарантирует, что у класса есть только один экземпляр, и предоставляет к нему глобальную точку доступа.
    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    //Метод возвращает путь файла XML
    public String getFILE_XML() {
        return Constans.FILE_PATH_XML;
    }

    //Метод возвращает путь файла JSON
    public String getFILE_JSON() {
        return Constans.FILE_PATH_JSON;
    }

    //Метод возвращает ссылку на путь файла
    public String getFilePath() {
        return filePath;
    }

    // Метод задает ссылку на путь файла
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //Метод скачивает файл
    @SuppressWarnings("static-access")
    public void downloadFile(String LINK) throws MyException, IOException {

        try {

            String link = LINK;
            InputStream inputStream = null;

            FileOutputStream outputStream = null;
            URL url = new URL(LINK);
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == httpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                String result = "";
                Pattern pattern = Pattern.compile(Constans.REGEX_FILES);
                Matcher matcher = pattern.matcher(link);

                if (matcher.find()) {
                    result = matcher.group();
                } else {
                    throw new MyException(Constans.TEXT_ERROR_DOWNLOADING);
                }

                File file = new File(Constans.FILE_PATH + result);
                outputStream = new FileOutputStream(file);
                int byteRead = -1;
                byte[] buffer = new byte[512];
                while ((byteRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, byteRead);
                }
            } else {
                throw new MyException(Constans.TEXT_ERROR_DOWNLOADING_RESPONSE);
            }
            inputStream.close();
            outputStream.close();
        } catch (UnknownHostException e) {
            throw new MyException(Constans.TEXT_ERROR_DOWNLOADING_UNKNOWNHOST);
        } catch (IOException e) {
            throw new MyException(Constans.TEXT_ERROR_DOWNLOADING_IOEXCEPTION);
        } catch (Exception e) {
            throw new MyException(Constans.TEXT_ERROR_DOWNLOADING_UNKNOW_ERROR);
        }
    }

    // Метод удаляет файл
    public void deleteFile(String filePath) throws MyException {

        File file = new File(filePath);
        if (!file.exists()) {
            throw new MyException(Constans.TEXT_ERROR_DELETE_FILE + filePath + Constans.TEXT_ERROR_DELETE_NOT_FOUND);
        }
        if (!file.delete()) {
            throw new MyException(Constans.TEXT_ERROR_DELETE);
        }
    }
}
