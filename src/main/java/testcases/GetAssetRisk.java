package testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utility.BaseClass;


import java.io.FileReader;
import java.io.IOException;


import static io.restassured.RestAssured.given;


public class GetAssetRisk extends BaseClass {


    @Test
    public void getAssetRisk() throws IOException, ParseException {
//        Object obj = new JSONParser().parse(new FileReader("src/test/resources/getAssetRisk.json"));

        // typecasting obj to JSONObject
        JSONObject jo =  ConfigFileReader.readFile("getAssetRisk.json");

        //initialization of data
        String url = jo.get("URL").toString();
        String hostname = jo.get("hostname").toString();
        String basePath = jo.get("basePath").toString() + "/" + hostname;
        String payload = jo.get("payload").toString();
        String ENGGID = jo.get("ENGGID").toString();
        ExtentTest test = extent.createTest(ENGGID+": Asset at risk");
        try {

            RestAssured.baseURI = url;
            RestAssured.basePath = basePath;

            Response response =
                    given().
                            when().get();


            if (response.getStatusCode() == 200) {
                JsonPath jsonPath = new JsonPath(response.asString());
                String getPayload = jsonPath.getString("payload");
                if (payload.equals(getPayload)) {
                    test.log(Status.PASS, "The risk level for " + hostname + " is " + payload);
                    test.log(Status.PASS, "The Liveness of RM calculator is True");
                }
            } else if (response.getStatusCode() >= 500 && response.getStatusCode() >= 600) {
                test.log(Status.FAIL, "RM calculator is down");
                test.fail("RM calculator is down");
                softAssert.fail();
            } else {
                softAssert.fail();
                test.log(Status.FAIL, "check configuration");
                test.fail("check configuration");
            }
        } catch (Exception e) {
            test.fail("RM calculator is down " + e);
            softAssert.fail(e.toString());
        }
    }

}
