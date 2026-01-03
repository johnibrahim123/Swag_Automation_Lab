package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataDriven {

    private static final String TEST_DATA_PATH = "src/test/resources/testData.json";

    /**
     * Reads test data from JSON file
     * @param dataType - "validCredentials" or "invalidCredentials"
     * @param field - "username" or "password"
     * @return test data value
     */
    public static String jsonReader(String dataType, String field) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(TEST_DATA_PATH)) {
            Object obj = parser.parse(reader);
            JSONObject testData = (JSONObject) obj;
            JSONObject credentials = (JSONObject) testData.get(dataType);

            return (String) credentials.get(field);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Test data file not found: " + TEST_DATA_PATH);
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Error reading test data: " + e.getMessage());
        }
    }
}