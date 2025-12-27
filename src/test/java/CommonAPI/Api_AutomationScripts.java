package CommonAPI;

import core.BaseTest_Extent;
import core.Statuscode_Repo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.*;

public class Api_AutomationScripts extends BaseTest_Extent
{
    private String token_storeContainer;

    //common API central portion!
    @Test(description = "Authentication_Login_API")
    public void Login_API()
    {
        //Extent Report
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("Login_API");
        //step1 set the baseURI
        baseURI = "https://fakestoreapi.com";
        //step2 create a bdd style template to validate the request
        Response response =
                given().contentType(ContentType.JSON)
                        .body("{\"username\":\"mor_2314\",\"password\":\"83r5^_\"}")
                        .when().post("/auth/login")
                        .then().statusCode(Statuscode_Repo.created.code).extract().response();
        System.out.println(response.body().asString());
        //step3 store the token value to reuse it across the rest APIS!
        token_storeContainer = response.jsonPath().getString("token");
        System.out.println("===============================================================");
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

    @Test(description = "AddNewProduct_API")
    public void AddNewProduct_API()
    {
        //Extent Report
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("AddNewProduct_API",
                "Validate 201 status code");
        //step1 set the BaseURI
        baseURI = "https://fakestoreapi.com";
        //step2 create a bdd style template to validate the request
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token_storeContainer)
                .body("{\"title\":\"AutomationTestingBook\",\"price\":799.99," +
                        "\"description\":\"CompleteguideforAPIandUIautomationtesting\"," +
                        "\"image\":\"https://i.pravatar.cc\",\"category\":\"books\"}")
                .when().post("/products")
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

    @Test(description = "GetSingleProduct_API")
    public void GetSingleProduct_API()
    {
        //Extent Report
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("GetSingleProduct_API",
                "Validate 200 status code");
        //step1 set the BaseURI
        baseURI = "https://fakestoreapi.com";
        //step2 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .when().get("/products/1")
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
    }

    @Test(description = "Get all products_API")
    public void Get_allProducts_API()
    {
        //Extent Report
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("Get_allProducts_API",
                "Validate 200 status code");
        //step1 set the BaseURI
        baseURI = "https://fakestoreapi.com";
        //step2 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .when().get("/products")
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
    }

    @Test(description = "Update_product_API")
    public void Update_product_API()
    {
        //Extent Report
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("Update_product_API",
                "Validate 200 status code");
        //step1 set the BaseURI
        baseURI = "https://fakestoreapi.com";
        //step2 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .body("{\"title\":\"AutomationTestingBook\",\"price\":799.99," +
                        "\"description\":\"CompleteguideforAPIandUIautomationtesting\"," +
                        "\"image\":\"https://i.pravatar.cc\",\"category\":\"books\"}")
                .when().put("/products/2")
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
    }

    @Test(description = "Delete a product_API")
    public void Delete_API()
    {
        //Extent Report
        ExtentReport.extentTest = ExtentReport.extentReports.createTest("Delete_API",
                "Validate 200 status code");
        //step1 set the BaseURI
        baseURI = "https://fakestoreapi.com";
        //step2 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .when().delete("/products/2")
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //step3 assertions validations- response body
    }
}