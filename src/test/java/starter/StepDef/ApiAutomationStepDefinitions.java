package starter.StepDef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.*;

public class ApiAutomationStepDefinitions {

    private static final String BASE_URL = "http://api.weatherbit.io/v2.0/";
    private static final String API_KEY = "7120448334944bbd998b9631e08d54c8";
    private static final String CURRENT_ENDPOINT = "/current";
    private static final String FORECAST_ENDPOINT = "/forecast/3hourly";

    private Response response;

    @Given("I have registered and obtained the API token key")
    public void registerAndGetApiKey() {
        // TODO: Implement the registration and obtaining of API token key
        // Set the API_KEY value with the obtained key
    }

    @When("I send a GET request to {string} with lat {string} and lon {string}")
    public void sendGetRequestToCurrentEndpoint(String endpoint, String lat, String lon) {
        response = given()
                .baseUri(BASE_URL)
                .basePath(endpoint)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("key", "7120448334944bbd998b9631e08d54c8")
                .contentType(ContentType.JSON)
                .when()
                .get();
    }

    @Then("the response should have status code {int}")
    public void verifyStatusCode(int statusCode) {
        response.then()
                .statusCode(statusCode);
    }

    @Then("the response should have property {string}")
    public void verifyResponseProperty(String property) {
        response.then()
                .body(property, notNullValue());
    }

    @When("I send a GET request to {string} with postal_code {string}")
    public void sendGetRequestToForecastEndpoint(String endpoint, String postalCode) {
        response = given()
                .baseUri(BASE_URL)
                .basePath(endpoint)
                .queryParam("postal_code", postalCode)
                .queryParam("key", API_KEY)
                .contentType(ContentType.JSON)
                .when()
                .get();
    }

    @Then("the response should have properties {string} and {string} for all data entries")
    public void verifyResponseProperties(String property1, String property2) {
        response.then()
                .body("data", everyItem(hasKey(property1)))
                .body("data", everyItem(hasKey(property2)));
    }
}
