package Products;

import constant.Endpoints;
import core.BaseTest_Extent;
import core.Statuscode_Repo;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.Updateproduct_API;
import utils.ExtentReport;
import java.io.File;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.ExtentReport.extentTest;

public class Update_productApiTest extends BaseTest_Extent
{
    @Test(description = "Update_product_API",priority = 1)
    public void UpdateProductApiTest_PTC001() {
        //step1 set the BaseURI
        baseURI = Endpoints.url;

        //step2 calling the pojo and set the test data
        Updateproduct_API pojo_call = new Updateproduct_API();
        pojo_call.setTitle("API- Automation rest assured");
        pojo_call.setPrice(200.89f);
        pojo_call.setDescription("Complete guide for API automation testing");
        pojo_call.setImage("https://i.pravatar.cc");
        pojo_call.setCategory("Library books");

        //step3 create a bdd style template to validate the request
        File schema = new File("src/test/resources/schema/UpdateproductApiTest.json");
        Response response = given().contentType(ContentType.JSON)
                .body(pojo_call)
                .when().put(Endpoints.path_url_Update_product_API)
                .then().statusCode(Statuscode_Repo.success.code).body(JsonSchemaValidator.matchesJsonSchema(schema)).extract().response();
        System.out.println("Json schema validation is success");
        System.out.println(response.body().asString());

        //Extent logs print
        extentTest.info("Requesting UpdateProductApiTest_PTC001");
        extentTest.info("Status Code: " + response.getStatusCode());
        extentTest.info("Response Body: " + response.asPrettyString());
        extentTest.assignCategory("Test Suite: " + " " + "Sanity");
        extentTest.assignAuthor("Dipti Ranjan Dash");
        extentTest.assignDevice("MacOS - Chrome");

        //step3 assertions validations- response body
    }

    @Test(description = "Update_product_API",priority = 2,enabled = false)
    public void UpdateProductApiTest_NTC002() {
        //step1 set the BaseURI
        baseURI = Endpoints.url;

        //step2 calling the pojo and set the test data
        Updateproduct_API pojo_call = new Updateproduct_API();
        pojo_call.setTitle("API- Automation rest assured");
        pojo_call.setPrice(200.89f);
        pojo_call.setDescription("Complete guide for API automation testing");
        pojo_call.setImage("https://i.pravatar.cc");
        pojo_call.setCategory("Library books");

        //step3 create a bdd style template to validate the request
        File schema = new File("src/test/resources/schema/UpdateproductApiTest.json");
        Response response = given().contentType(ContentType.JSON)
                .body(pojo_call)
                .when().post(Endpoints.path_url_Update_product_API)
                .then().statusCode(Statuscode_Repo.not_found.code).body(JsonSchemaValidator.matchesJsonSchema(schema)).extract().response();
        System.out.println("Json schema validation is success");
        System.out.println(response.body().asString());

        //Extent logs print
        extentTest.info("Requesting UpdateProductApiTest_NTC002");
        extentTest.info("Status Code: " + response.getStatusCode());
        extentTest.info("Response Body: " + response.asPrettyString());
        extentTest.assignCategory("Test Suite: " + " " + "Sanity");
        extentTest.assignAuthor("Dipti Ranjan Dash");
        extentTest.assignDevice("MacOS - Chrome");

        //step3 assertions validations- response body
    }
}
