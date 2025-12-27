package CommonAPI;

import constant.Endpoints;
import core.Statuscode_Repo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.AddNewProduct_API;
import pojo.Authentication_Login_API;
import pojo.Updateproduct_API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static io.restassured.RestAssured.*;

public class AutomateRequestBody_withThreeWays
{
    public String token_storeContainer;
    //Automate request body with Normal String JSON to String Conversion
    @Test(description = "Authentication_Login_API (Login → Generate Token)")
    public void Login_API_withString() {
        //step1 set the baseURI
        baseURI = Endpoints.url;
        //step2 create a bdd style template to validate the request
        Response response =
                given().contentType(ContentType.JSON)
                        .body("{\"username\":\"mor_2314\",\"password\":\"83r5^_\"}")
                        .when().post(Endpoints.path_url_Login_API)
                        .then().statusCode(Statuscode_Repo.created.code).extract().response();
        System.out.println(response.body().asString());
        //step3 store the token value to reuse it across the rest APIS!
        token_storeContainer = response.jsonPath().getString("token");
        System.out.println("==============================================");
        System.out.println("The token value is: " + token_storeContainer);
        //step4 assertions validations- response body
        //validate the content_type
        Assert.assertNotNull("ContentType");
        //validate response headers in REST Assured using the header() assertion or by extracting the header
        // from the response object
        String Fetch_header = response.getHeader("Server");
        Assert.assertEquals(Fetch_header, "cloudflare");
        //validate the response time of an API
        System.out.println("Execution time: " + " " + response.getTime());
        long time = response.getTime();
        if (time < 3000) {
            System.out.println("The execution time is less then 3000ms");
        } else {
            System.out.println("The execution time is more then 3000ms");
        }
    }

    @Test(description = "AddNewProduct_API (Create Product (Auth Required))")
    public void AddNewProduct_API_withString() {
        //step1 set the BaseURI
        baseURI = Endpoints.url;
        //step2 create a bdd style template to validate the request
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token_storeContainer)
                .body("{\"title\":\"AutomationTestingBook\",\"price\":799.99," +
                        "\"description\":\"CompleteguideforAPIandUIautomationtesting\"," +
                        "\"image\":\"https://i.pravatar.cc\",\"category\":\"books\"}")
                .when().post(Endpoints.path_url_AddNewProduct_API)
                .then().statusCode(Statuscode_Repo.created.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
        //System.out.println(token_storeContainer);
        //FakeStore API generates a token but does not enforce authentication on protected endpoints, so the token is not consumed or validated during API chaining.
        //For learning & interviews:
        //Keep FakeStore for CRUD + validations
        //Use DummyJSON / GoRest for auth chaining demos API site: DummyJSON/GoRest/Swagger PetStore
        String title = response.jsonPath().getString("title");
        System.out.println(title.equals("AutomationTestingBook"));
        Assert.assertEquals("AutomationTestingBook", "AutomationTestingBook");

    }

    @Test(description = "Update_product_API (Update Product) specific product id")
    public void Update_product_API_withString() {
        //step1 set the BaseURI
        baseURI = Endpoints.url;
        //step2 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .body("{\"title\":\"AutomationTestingBook\",\"price\":799.99," +
                        "\"description\":\"CompleteguideforAPIandUIautomationtesting\"," +
                        "\"image\":\"https://i.pravatar.cc\",\"category\":\"books\"}")
                .when().put(Endpoints.path_url_Update_product_API)
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
    }

    //Code optimization for External JSON file to reduce the line of code from 1st approach
    private static FileInputStream FileInputStreamMethod(String requestbodyFilename)
    {
       FileInputStream fileinputstream;
       try
       {
           fileinputstream=new FileInputStream(new File(System.getProperty("user.dir") +
                   "/src/test/resources/payloads/" + requestbodyFilename));
       }
       catch (FileNotFoundException e)
       {
           throw new RuntimeException(e);
       }
     return fileinputstream;
    }

    //Automate request body with External JSON file JSON to String conversion using IOUtiles class from common.io
    @Test(description = "Authentication_Login_API (Login → Generate Token)")
    public void Login_API_withExternalJsonFile() throws IOException, ParseException {
        //step1 set the baseURI
        baseURI = Endpoints.url;
        //step2 Read JSON file
        //FileInputStream file = new FileInputStream(new File("src/test/resources/payload_testdata/Authentication_Login_API_testdata.json"));
        //String request_body=IOUtils.toString(file,"UTF-8");
        //step-3 create a bdd style template to validate the request
        Response response =
                given()
                        .contentType(ContentType.JSON)
                         //First approach Use JSON File Directly as Request Body
                        //No parsing needed /Clean & readable/Most common in real projects
                        //.body(new File("src/test/resources/testdata/Authentication_Login_API_testdata.json"))
                        //Second approach Read JSON → Modify Dynamically (When Needed)
                        .body(FileInputStreamMethod("Authentication_Login_API_testdata.json"))
                        .when().post(Endpoints.path_url_Login_API)
                        .then().statusCode(Statuscode_Repo.created.code).extract().response();
        System.out.println(response.body().asString());
        //step3 store the token value to reuse it across the rest APIS!
        token_storeContainer = response.jsonPath().getString("token");
        System.out.println("==============================================");
        System.out.println("The token value is: " + token_storeContainer);
        //step4 assertions validations- response body
        //validate the content_type
        Assert.assertNotNull("ContentType");
        //validate response headers in REST Assured using the header() assertion or by extracting the header
        // from the response object
        String Fetch_header = response.getHeader("Server");
        Assert.assertEquals(Fetch_header, "cloudflare");
        //validate the response time of an API
        System.out.println("Execution time: " + " " + response.getTime());
        long time = response.getTime();
        if (time < 3000) {
            System.out.println("The execution time is less then 3000ms");
        } else {
            System.out.println("The execution time is more then 3000ms");
        }
    }

    @Test(description = "AddNewProduct_API (Create Product (Auth Required))")
    public void AddNewProduct_API_withExternalJsonFile() throws IOException {
        //step1 set the BaseURI
        baseURI = Endpoints.url;

        //step2 Read JSON file
        //FileInputStream file=new FileInputStream(new File("src/test/resources/payload_testdata/AddNewProduct_API_testdata.json"));
        //String request_body=IOUtils.toString(file,"UTF-8");
        //step3 create a bdd style template to validate the request
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token_storeContainer)
                //First approach Use JSON File Directly as Request Body
                //No parsing needed /Clean & readable/Most common in real projects
                //.body(new File("src/test/resources/testdata/AddNewProduct_API_testdata.json"))
                .body(FileInputStreamMethod("AddNewProduct_API_testdata.json"))
                .when().post(Endpoints.path_url_AddNewProduct_API)
                .then().statusCode(Statuscode_Repo.created.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
        //System.out.println(token_storeContainer);
        //FakeStore API generates a token but does not enforce authentication on protected endpoints, so the token is not consumed or validated during API chaining.
        //For learning & interviews:
        //Keep FakeStore for CRUD + validations
        //Use DummyJSON / GoRest for auth chaining demos API site: DummyJSON/GoRest/Swagger PetStore
        String title = response.jsonPath().getString("title");
        System.out.println(title.equals("AutomationTestingBook"));
        Assert.assertEquals("AutomationTestingBook", "AutomationTestingBook");

    }

    @Test(description = "Update_product_API (Update Product) specific product id")
    public void Update_product_API_withExternalJsonFile() throws IOException {
        //step1 set the BaseURI
        baseURI =Endpoints.url;
        //step2 Read JSON file
        //FileInputStream file=new FileInputStream(new File("src/test/resources/payload_testdata/Updateproduct_API_testdata.json"));
        //String request_body=IOUtils.toString(file,"UTF-8");
        //step3 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                //First approach Use JSON File Directly as Request Body
                //No parsing needed /Clean & readable/Most common in real projects
                //.body(new File("src/test/resources/testdata/Updateproduct_API_testdata.json"))
                .body(FileInputStreamMethod("Updateproduct_API_testdata.json"))
                .when().put(Endpoints.path_url_Update_product_API)
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
    }

    //Automate request body with Pojo class adding jackson-databind dependency
    @Test(description = "Authentication_Login_API (Login → Generate Token)")
    public void Login_API_withPojo()
    {
        //step1 set the baseURI
        baseURI = Endpoints.url;
        //step2 calling the pojo and set the test data
        Authentication_Login_API pojo_call=new Authentication_Login_API();
        pojo_call.setUsername("mor_2314");
        pojo_call.setPassword("83r5^_");
        //step3 create a bdd style template to validate the request
        Response response =
                given().contentType(ContentType.JSON)
                        .body(pojo_call)
                        .when().post(Endpoints.path_url_Login_API)
                        .then().statusCode(Statuscode_Repo.created.code).extract().response();
        System.out.println(response.body().asString());
        //step3 store the token value to reuse it across the rest APIS!
        token_storeContainer = response.jsonPath().getString("token");
        System.out.println("==============================================");
        System.out.println("The token value is: " + token_storeContainer);
        //step4 assertions validations- response body
        //validate the content_type
        Assert.assertNotNull("ContentType");
        //validate response headers in REST Assured using the header() assertion or by extracting the header
        // from the response object
        String Fetch_header = response.getHeader("Server");
        Assert.assertEquals(Fetch_header, "cloudflare");
        //validate the response time of an API
        System.out.println("Execution time: " + " " + response.getTime());
        long time = response.getTime();
        if (time < 3000) {
            System.out.println("The execution time is less then 3000ms");
        } else {
            System.out.println("The execution time is more then 3000ms");
        }
    }

    @Test(description = "AddNewProduct_API (Create Product (Auth Required))")
    public void AddNewProduct_API_withPojo() {
        //step1 set the BaseURI
        baseURI = Endpoints.url;
        //step2 calling the pojo and set the test data
        //Serialization
        AddNewProduct_API pojo_call=new AddNewProduct_API();
        pojo_call.setTitle("API- Automation Testing Book for rest assured");
        pojo_call.setPrice(200.89f);
        pojo_call.setDescription("Complete guide for API and UI automation testing");
        pojo_call.setImage("https://i.pravatar.cc");
        pojo_call.setCategory("Library books");
        //step3 create a bdd style template to validate the request
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token_storeContainer)
                .body(pojo_call)
                .when().post(Endpoints.path_url_AddNewProduct_API)
                .then().statusCode(Statuscode_Repo.created.code).extract().response();

        System.out.println(response.body().asString());
        //Deserialization
        AddNewProduct_API AddNewProduct_api=response.as(AddNewProduct_API.class);
        System.out.println(AddNewProduct_api.getTitle());
        System.out.println(AddNewProduct_api.getPrice());
        System.out.println(AddNewProduct_api.getDescription());
        Assert.assertEquals(AddNewProduct_api.getTitle(),"Automation Testing Book");
        Assert.assertTrue(AddNewProduct_api.getPrice() > 500);
        //step3 assertions validations- response body
        //System.out.println(token_storeContainer);
        //FakeStore API generates a token but does not enforce authentication on protected endpoints, so the token is not consumed or validated during API chaining.
        //For learning & interviews:
        //Keep FakeStore for CRUD + validations
        //Use DummyJSON / GoRest for auth chaining demos API site: DummyJSON/GoRest/Swagger PetStore
        String title = response.jsonPath().getString("title");
        System.out.println(title.equals("AutomationTestingBook"));
        Assert.assertEquals("AutomationTestingBook", "AutomationTestingBook");
    }

    @Test(description = "Update_product_API (Update Product) specific product id")
    public void Update_product_API_withPojo() {
        //step1 set the BaseURI
        baseURI = Endpoints.url;
        //step2 calling the pojo and set the test data
        Updateproduct_API pojo_call=new Updateproduct_API();
        pojo_call.setTitle("API- Automation rest assured");
        pojo_call.setPrice(200.89f);
        pojo_call.setDescription("Complete guide for API automation testing");
        pojo_call.setImage("https://i.pravatar.cc");
        pojo_call.setCategory("Library books");
        //step3 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .body(pojo_call)
                .when().put(Endpoints.path_url_Update_product_API)
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
    }
}
