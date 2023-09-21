package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.BaseUtils;
import resources.TestDataBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinitions extends BaseUtils {
    RequestSpecification requestSpecification;
    ResponseSpecification res;
    Response response;
    public static String place_ID;
    TestDataBuilder testData = new TestDataBuilder();

    @Given("Add Place Payload with {string} {string} and {string}")
    public void addPlacePayloadWithAnd(String name, String address, String language) throws IOException {
        requestSpecification = given().spec(requestSpecification()).body(testData.addPlacePayload(name, address, language));
    }

    @When("user calls {string} with {string} HTTP request")
    public void userCallsWithHTTPRequest(String API, String httpMethod) {
        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        APIResources apiResources = APIResources.valueOf(API);
        if (httpMethod.equalsIgnoreCase("POST"))
            response = requestSpecification.when().post(apiResources.getResource());
        else if (httpMethod.equalsIgnoreCase("GET"))
            response = requestSpecification.when().get(apiResources.getResource());
    }

    @Then("the API call should be success with {string}")
    public void the_api_call_should_be_success_with(String statusCode) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String responseField, String expectedValue) {
        String resp = response.asString();
        JsonPath jp = new JsonPath(resp);
        String actualValue = jp.getString(responseField);
        Assert.assertEquals(actualValue, expectedValue);
        place_ID = jp.getString("place_id");

    }


    @And("Verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String name, String resource) throws IOException {
        requestSpecification = given().spec(requestSpecification()).queryParam("place_id", place_ID);

        userCallsWithHTTPRequest(resource, "GET");
        JsonPath jp = new JsonPath(response.asString());
        Assert.assertEquals(name, jp.getString("name"));
        System.out.println(place_ID);
    }

    @Given("Delete Place Payload")
    public void deletePlacePayload() throws IOException {
        System.out.println(place_ID);
        requestSpecification = given().spec(requestSpecification()).body(testData.deletePlacePayload(place_ID));

    }
}
