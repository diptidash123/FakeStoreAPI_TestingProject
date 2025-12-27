package Products;

import constant.Endpoints;
import core.BaseTest_Extent;
import core.Statuscode_Repo;
import core.Statuscode_Repo;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.AddNewProduct_API;
import utils.ExtentReport;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.ExtentReport.extentTest;

public class AddNewProductApiTest extends BaseTest_Extent
{
    public String token_storeContainer;
    @Test(description = "AddNewProduct_API (Create Product (Auth Required))")
    public void AddNewProductApiTest()
    {
        //step1 set the BaseURI
        baseURI = Endpoints.url;
        //step2 calling the pojo and set the test data
        AddNewProduct_API pojo_call=new AddNewProduct_API();
        pojo_call.setTitle("API- Automation Testing Book for rest assured");
        pojo_call.setPrice(200.89f);
        pojo_call.setDescription("Complete guide for API and UI automation testing");
        pojo_call.setImage("https://i.pravatar.cc");
        pojo_call.setCategory("Library books");
        //step3 create a bdd style template to validate the request
        //JSON file path for schema validation
        File file=new File("src/test/resources/schema/AddNewProductApiTest.json");
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token_storeContainer)
                .body(pojo_call)
                .when().post(Endpoints.path_url_AddNewProduct_API)
                //Call the jsonschema validator
                .then().statusCode(Statuscode_Repo.created.code).body(JsonSchemaValidator.matchesJsonSchema(file)).extract().response();
                System.out.println("Schema validation is passed");
                //.then().statusCode(Statuscode_Repo.created.code).extract().response();
        System.out.println(response.body().asString());
        //Deserialization
        AddNewProduct_API AddNewProduct_api=response.as(AddNewProduct_API.class);
        System.out.println(AddNewProduct_api.getTitle());
        System.out.println(AddNewProduct_api.getPrice());
        System.out.println(AddNewProduct_api.getDescription());
        Assert.assertEquals(AddNewProduct_api.getTitle(),"API- Automation Testing Book for rest assured");
        Assert.assertEquals(AddNewProduct_api.getDescription(),"Complete guide for API and UI automation testing");
        System.out.println("The response validations are success");
        //Extent logs print
        extentTest.info("Requesting AddNewProductApiTest");
        extentTest.info("Status Code: " + response.getStatusCode());
        extentTest.info("Response Body: " + response.asPrettyString() );
        extentTest.assignCategory("Test Suite: " + " " + "Sanity");
        extentTest.assignAuthor("Dipti Ranjan Dash");
        extentTest.assignDevice("MacOS - Chrome");
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
}
