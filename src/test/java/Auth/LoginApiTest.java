package Auth;

import constant.Endpoints;
import core.BaseTest_Extent;
import core.Statuscode_Repo;
import core.Statuscode_Repo;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Authentication_Login_API;
import utils.ExtentReport;
import utils.FailedRetryAnalyzerReader;
import utils.SoftAssertionsReader;
import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.ExtentReport.extentTest;


public class LoginApiTest extends BaseTest_Extent
{
    public String token_storeContainer;
    //Apply Retry to Test Case
    //If this test fails → it will auto re-run up to 2 times
    //Positive testcase
    @Test(description = "Authentication_Login_API",priority = 1)
    public void LoginApiTest_PTC001() throws IOException

    {
        //step1 set the baseURI
        baseURI = Endpoints.url;

        //step2 calling the pojo and set the test data
        Authentication_Login_API pojo_call = new Authentication_Login_API();
        pojo_call.setUsername("mor_2314");
        pojo_call.setPassword("83r5^_");

        //step3 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .body(pojo_call)
                .when().post(Endpoints.path_url_Login_API)
                .then().statusCode(Statuscode_Repo.created.code).extract().response();
        //Extent logs print
        extentTest.info("Requesting LoginApiTest_PTC001");
        extentTest.info("Status Code: " + response.getStatusCode());
        extentTest.info("Response Body: " + response.asPrettyString());
        extentTest.assignCategory("Test Suite: " + " " + "Sanity");
        extentTest.assignAuthor("Dipti Ranjan Dash");
        extentTest.assignDevice("MacOS - Chrome");
        System.out.println(response.body().asString());

        //step4 store the token value to reuse it across the rest APIS!
        token_storeContainer = response.jsonPath().getString("token");
        System.out.println("==============================================");
        System.out.println("The token value is: " + token_storeContainer);

        //step5 assertions validations- response body
        //Initialize the SoftAssertionsReader class
        SoftAssertionsReader.initialize();

        //Validate token is not null
        SoftAssertionsReader.assertNotNull(token_storeContainer, "The token is not null after login");
        System.out.println("The token was validated successfully");

        //Validate the ContentType
        SoftAssertionsReader.assertNotNull("Content-Type", "The Content-Type is not null");
        System.out.println("The Content-Type was validated successfully");

        //Validate the response header should have Server = cloudflare
        String fetch_server_key = response.getHeader("Server");
        SoftAssertionsReader.assertEquals(fetch_server_key, "cloudflare", "The Server = cloudfare");
        System.out.println("The response header server_key was validated successfully");

        //Validate the response time of an API
        System.out.println("Execution time: " + " " + response.getTime());
        long time = response.getTime();
        if (time < 3000) {
            System.out.println("The execution time is less then 3000ms");
        } else {
            System.out.println("The execution time is more then 3000ms");
        }
        SoftAssertionsReader.assertAll();
    }

        //Negative testcases
        //Apply Retry to Test Case
        //If this test fails → it will auto re-run up to 2 times
        @Test(description = "Authentication_Login_API",priority = 2, enabled = false)
        public void LoginApiTest_NTC002() throws IOException
        {
            //step1 set the baseURI
            baseURI = Endpoints.url;

            //step2 calling the pojo and set the test data
            Authentication_Login_API pojo_call = new Authentication_Login_API();
            pojo_call.setUsername(" ");
            pojo_call.setPassword(" ");

            //step3 create a bdd style template to validate the request
            Response response = given().contentType(ContentType.JSON)
                    .body(pojo_call)
                    .when().post(Endpoints.path_url_Login_API)
                    .then().statusCode(Statuscode_Repo.bad_request.code).extract().response();
            //Extent logs print
            extentTest.info("Requesting LoginApiTest_NTC002");
            extentTest.info("Status Code: " + response.getStatusCode());
            extentTest.info("Response Body: " + response.asPrettyString());
            extentTest.assignCategory("Test Suite: " + " " + "Sanity");
            extentTest.assignAuthor("Dipti Ranjan Dash");
            extentTest.assignDevice("MacOS - Chrome");
            System.out.println(response.body().asString());

            //step4 store the token value to reuse it across the rest APIS!
            token_storeContainer = response.jsonPath().getString("token");
            System.out.println("==============================================");
            System.out.println("The token value is: " + token_storeContainer);

            //step5 assertions validations- response body
            //Initialize the SoftAssertionsReader class
            SoftAssertionsReader.initialize();

            //Validate the ContentType
            SoftAssertionsReader.assertNotNull("Content-Type", "The Content-Type is not null");
            System.out.println("The Content-Type was validated successfully");

            //Validate the response header should have Server = cloudflare
            String fetch_server_key = response.getHeader("Server");
            SoftAssertionsReader.assertEquals(fetch_server_key, "cloudflare", "The Server = cloudfare");
            System.out.println("The response header server_key was validated successfully");

            //Validate the response time of an API
            System.out.println("Execution time: " + " " + response.getTime());
            long time = response.getTime();
            if (time < 3000) {
                System.out.println("The execution time is less then 3000ms");
            } else {
                System.out.println("The execution time is more then 3000ms");
            }
            SoftAssertionsReader.assertAll();
        }
         //Negative testcases
        //Apply Retry to Test Case
        //If this test fails → it will auto re-run up to 2 times
        @Test(description = "Authentication_Login_API",priority = 3,enabled = false)
        public void LoginApiTest_NTC003() throws IOException
        {
            //step1 set the baseURI
            baseURI = Endpoints.url;

            //step2 calling the pojo and set the test data
            Authentication_Login_API pojo_call = new Authentication_Login_API();
            pojo_call.setUsername("mor_2314");
            pojo_call.setPassword("83r5^_YUYJYU");

            //step3 create a bdd style template to validate the request
            Response response = given().contentType(ContentType.JSON)
                    .body(pojo_call)
                    .when().post(Endpoints.path_url_Login_API)
                    .then().statusCode(Statuscode_Repo.un_authorized.code).extract().response();
            //Extent logs print
            extentTest.info("Requesting LoginApiTest_NTC003");
            extentTest.info("Status Code: " + response.getStatusCode());
            extentTest.info("Response Body: " + response.asPrettyString());
            extentTest.assignCategory("Test Suite: " + " " + "Sanity");
            extentTest.assignAuthor("Dipti Ranjan Dash");
            extentTest.assignDevice("MacOS - Chrome");
            System.out.println(response.body().asString());

            //step4 store the token value to reuse it across the rest APIS!
            token_storeContainer = response.jsonPath().getString("token");
            System.out.println("==============================================");
            System.out.println("The token value is: " + token_storeContainer);

            //step5 assertions validations- response body
            //Initialize the SoftAssertionsReader class
            SoftAssertionsReader.initialize();

            //Validate the ContentType
            SoftAssertionsReader.assertNotNull("Content-Type", "The Content-Type is not null");
            System.out.println("The Content-Type was validated successfully");

            //Validate the response header should have Server = cloudflare
            String fetch_server_key = response.getHeader("Server");
            SoftAssertionsReader.assertEquals(fetch_server_key, "cloudflare", "The Server = cloudfare");
            System.out.println("The response header server_key was validated successfully");

            //Validate the response time of an API
            System.out.println("Execution time: " + " " + response.getTime());
            long time = response.getTime();
            if (time < 3000) {
                System.out.println("The execution time is less then 3000ms");
            } else {
                System.out.println("The execution time is more then 3000ms");
            }
            SoftAssertionsReader.assertAll();
        }

        //Negative testcases
        //Apply Retry to Test Case
        //If this test fails → it will auto re-run up to 2 times
        @Test(description = "Authentication_Login_API",priority = 4,enabled = false)
        public void LoginApiTest_NTC004() throws IOException
        {
            //step1 set the baseURI
            baseURI = Endpoints.url;

            //step2 calling the pojo and set the test data
            Authentication_Login_API pojo_call = new Authentication_Login_API();
            pojo_call.setUsername("mor_2314123456");
            pojo_call.setPassword("83r5^_");

            //step3 create a bdd style template to validate the request
            Response response = given().contentType(ContentType.JSON)
                    .body(pojo_call)
                    .when().post(Endpoints.path_url_Login_API)
                    .then().statusCode(Statuscode_Repo.un_authorized.code).extract().response();
            //Extent logs print
            extentTest.info("Requesting LoginApiTest_NTC004");
            extentTest.info("Status Code: " + response.getStatusCode());
            extentTest.info("Response Body: " + response.asPrettyString());
            extentTest.assignCategory("Test Suite: " + " " + "Sanity");
            extentTest.assignAuthor("Dipti Ranjan Dash");
            extentTest.assignDevice("MacOS - Chrome");
            System.out.println(response.body().asString());

            //step4 store the token value to reuse it across the rest APIS!
            token_storeContainer = response.jsonPath().getString("token");
            System.out.println("==============================================");
            System.out.println("The token value is: " + token_storeContainer);

            //step5 assertions validations- response body
            //Initialize the SoftAssertionsReader class
            SoftAssertionsReader.initialize();

            //Validate the ContentType
            SoftAssertionsReader.assertNotNull("Content-Type", "The Content-Type is not null");
            System.out.println("The Content-Type was validated successfully");

            //Validate the response header should have Server = cloudflare
            String fetch_server_key = response.getHeader("Server");
            SoftAssertionsReader.assertEquals(fetch_server_key, "cloudflare", "The Server = cloudfare");
            System.out.println("The response header server_key was validated successfully");

            //Validate the response time of an API
            System.out.println("Execution time: " + " " + response.getTime());
            long time = response.getTime();
            if (time < 3000) {
                System.out.println("The execution time is less then 3000ms");
            } else {
                System.out.println("The execution time is more then 3000ms");
            }
            SoftAssertionsReader.assertAll();
        }
}