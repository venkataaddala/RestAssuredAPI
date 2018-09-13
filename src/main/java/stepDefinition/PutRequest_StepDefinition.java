package stepDefinition;

import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.runner.Request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonObject;

public class PutRequest_StepDefinition {
	public Response response;
	public ValidatableResponse json;
	public Request request;
    
    public String EndPoint="https://reqres.in";
	
    public RequestSpecification requestspec=RestAssured.given().baseUri(EndPoint);
    
    @Given("^put url request$")
    public void put_url_request() {
    	JsonObject requestparams=new JsonObject();
	    requestparams.addProperty("name", "morpheus");
	    requestparams.addProperty("job", "zion resident");
	    
	    requestspec.header("Content-Type", "application/json");
	    requestspec.body(requestparams.toString());
    }

    @When("^user put request$")
    public void user_put_request() {
    	response=requestspec.put("/api/users/2");
	    System.out.println(response.prettyPrint());
    }

    @Then("^put request status code is (\\d+)$")
    public void put_request_status_code_is(int statusCode) {
    	json=response.then().statusCode(statusCode);
    }

    @And("^put response body contains$")
    public void put_response_body_contains(Map<String,String> responseFields) {
    	for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
    }

    @And("^put response headers contains$")
    public void put_response_headers_contains(Map<String,String> responseFields) {
    	for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.header(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.header(field.getKey(), equalTo(field.getValue()));
			}
		}
    }
}
