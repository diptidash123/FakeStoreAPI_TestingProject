package Products;
import core.BaseTest_Extent;
import core.Statuscode_Repo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.ExtentReport.extentTest;

public class DeleteProductApiTest extends BaseTest_Extent
{
    @Test(description = "Delete a product_API (Delete Product) specific product id")
    public void Delete_APITest() {
        //step1 set the BaseURI
        baseURI = "https://fakestoreapi.com";
        //step2 create a bdd style template to validate the request
        Response response = given().contentType(ContentType.JSON)
                .when().delete("/products/2")
                .then().statusCode(Statuscode_Repo.success.code).extract().response();
        System.out.println(response.body().asString());
        //Extent logs print
        extentTest.info("Requesting Delete_APITest");
        extentTest.info("Status Code: " + response.getStatusCode());
        extentTest.info("Response Body: " + response.asPrettyString() );
        extentTest.assignCategory("Test Suite: " + " " + "Sanity");
        extentTest.assignAuthor("Dipti Ranjan Dash");
        extentTest.assignDevice("MacOS - Chrome");
        //step3 assertions validations- response body
    }
}
