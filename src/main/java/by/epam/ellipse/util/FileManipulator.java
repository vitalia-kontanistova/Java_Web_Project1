package by.epam.ellipse.util;

import by.epam.ellipse.dao.exception.DAOexception;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileManipulator {
    //имя файла с данными в properties (еще не настроила работу через конфиги)
    //String request = "ellipse.base" - исходные данные
    //String request = "test.ellipse.base" - тестовые данные

    private static FileManipulator instance = new FileManipulator();

    private FileManipulator() {
    }

    public static FileManipulator getInstance() {
        return instance;
    }

    public List<String> extractEntriesFromFile(String requestToPropFile) throws DAOexception {
        String path = getFileAddress(requestToPropFile);

        DataValidator dataValidator = DataValidator.getInstance();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            List<String> aLLEntries = new ArrayList<>();

            String entry;

            while ((entry = bufferedReader.readLine()) != null) {
                if (dataValidator.isEllipseValid(entry)) {
                    aLLEntries.add(entry);
                }
            }

            return aLLEntries;

        } catch (NullPointerException | IOException | DAOexception e) {
            throw new DAOexception("FileManipulator: extractEntriesFromFile(): " + e.getMessage());
        }
    }

    private String getFileAddress(String requestToPropFile) throws DAOexception {
        try (FileInputStream fis = new FileInputStream("src/main/resources/prop.properties")) {

            Properties property = new Properties();
            property.load(fis);

            return property.getProperty(requestToPropFile);

        } catch (NullPointerException | IOException e) {
            throw new DAOexception("FileManipulator: getFileAddress(): property file is not found. " + e.getMessage());
        }
    }
}