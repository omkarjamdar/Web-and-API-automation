package testcases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigFileReader {
    private static String filePath;
    public ConfigFileReader(String filePath1)
    {
        ConfigFileReader.filePath = filePath1 + "/resources/";
    }
    public static JSONObject readFile(String filename) {
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader(ConfigFileReader.filePath + filename));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return (JSONObject) obj;
    }
}
