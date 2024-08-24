package testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utility.BaseClass;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class HealthCheckAPI extends BaseClass {

    public Response apiRespponse(String authorization, String URL) {
        return given().
                header("Authorization", authorization).
                get(URL);
    }

    @Test
    public void getHealthCheckAPI() throws IOException, ParseException {

//        Object obj = new JSONParser().parse(new FileReader("src/test/resources/getHealthCheckAPI.json"));

        // typecasting obj to JSONObject
        JSONObject jo = ConfigFileReader.readFile("getHealthCheckAPI.json");

        //initialization of data
        String reportParserLiveness = jo.get("reportParserLiveness").toString();
        String RMCalculatorLiveness = jo.get("RMCalculatorLiveness").toString();
        String reportParserReadiness = jo.get("reportParserReadiness").toString();
        String RMCalculatorReadiness = jo.get("RMCalculatorReadiness").toString();
        String ENGGID = jo.get("ENGGID").toString();
        String authorization = jo.get("Authorization").toString();
        ExtentTest test = extent.createTest(ENGGID + ": Health check API ");
        try {



            //Parser liveness API
            Response ParserLivnessResponse = apiRespponse(authorization, reportParserLiveness);
            String status="";
            if (ParserLivnessResponse.getStatusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(ParserLivnessResponse.asString());
                status = jsonResponse.get("status").asText();
                System.out.println(status);
                if (status == "true") {
                    test.log(Status.PASS, "The Liveness of Report Parser is True");
                    softAssert.assertEquals("true",status);
                } else {
                    test.log(Status.FAIL, "Report Parser is Down");
                    softAssert.fail();
                }
            } else {
                test.log(Status.FAIL, "Please check Report parser configuration");
                softAssert.assertEquals("true",status);
            }

            //RM calculator liveness API
            Response RMLivnessResponse = apiRespponse(authorization, RMCalculatorLiveness);
             status="false";
            if (RMLivnessResponse.getStatusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(RMLivnessResponse.asString());
                status = jsonResponse.get("status").asText();
                System.out.println(status);
                if (status == "true") {
                    test.log(Status.PASS, "The Liveness of RM calculator is True");
                    softAssert.assertEquals("true",status);
                } else {
                    test.log(Status.FAIL, "RM calculator is Down");
                    softAssert.fail();
                }
            } else {
                test.log(Status.FAIL, "Please check RM calculator configuration");
                softAssert.assertEquals("true",status);
            }


            //Parser readiness API
            Response ParserReadinessResponse = apiRespponse(authorization, reportParserReadiness);
            status="false";
            if (ParserReadinessResponse.getStatusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(ParserReadinessResponse.asString());
                status = jsonResponse.get("status").asText();
                System.out.println(status);
                if (status == "true") {
                    test.log(Status.PASS, "The Readiness of Report Parser is True");
                    softAssert.assertEquals("true",status);
                } else {
                    test.log(Status.FAIL, "Report Parser is Down due to <br>" + jsonResponse);
                    softAssert.fail();
                }
            } else {
                test.log(Status.FAIL, "Please check Report parser configuration");
                softAssert.assertEquals("true",status);
            }

            //RM calculator readiness API
            Response RMReadinessResponse = apiRespponse(authorization, RMCalculatorReadiness);
            status="false";
            if (RMReadinessResponse.getStatusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(RMReadinessResponse.asString());
                status = jsonResponse.get("status").asText();
                System.out.println(status);
                if (status == "true") {
                    test.log(Status.PASS, "The Readniess of RM calculator is True");
                    softAssert.assertEquals("true",status);
                } else {
                    test.log(Status.FAIL, "RM calculator is Down due to <br>" + jsonResponse);
                    softAssert.fail();
                }
            } else {
                test.log(Status.FAIL, "Please check RM calculator configuration");
                softAssert.assertEquals("true",status);
            }

        } catch (Exception e) {
            test.fail( e);
            softAssert.fail(e.toString());
        }
    }

}
