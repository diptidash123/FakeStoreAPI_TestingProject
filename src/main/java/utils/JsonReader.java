package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.IOException;

public class JsonReader
{
    public static JSONObject getJsonData() throws IOException, ParseException {
        //pass the path of the testdata.json file
        File filename = new File("src/test/resources/testdata/Authentication_Login_API_testdata.json");

        //convert JSON file into string
        String json = FileUtils.readFileToString(filename, "UTF-8");

        //parse the string into object
        Object obj = new JSONParser().parse(json);

        //give JSONObject so that I can return it to the function everytime it get called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
    public static String getTestData(String key) throws IOException, ParseException
    {
        String test_data;
        return test_data = (String) getJsonData().get(key);//input is the key

    }

}
