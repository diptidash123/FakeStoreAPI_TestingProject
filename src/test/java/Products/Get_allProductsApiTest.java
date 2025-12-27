package Products;

import constant.Endpoints;
import core.BaseTest_Extent;
import core.Statuscode_Repo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.ExtentReport.extentTest;

public class Get_allProductsApiTest extends BaseTest_Extent
{
    @Test(description = "Get all products_API (Get Products)")
    public void Get_allProducts_APITest() {
        //step1 set the BaseURI
        baseURI = Endpoints.url;
        //step2 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .when().get(Endpoints.path_url_Get_all_productsAPI_API)
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //Extent logs print
        extentTest.info("Requesting Get_allProducts_APITest");
        extentTest.info("Status Code: " + response.getStatusCode());
        extentTest.info("Response Body: " + response.asPrettyString() );
        extentTest.assignCategory("Test Suite: " + " " + "Sanity");
        extentTest.assignAuthor("Dipti Ranjan Dash");
        extentTest.assignDevice("MacOS - Chrome");
        //step3 assertions validations- response body
    }
}
