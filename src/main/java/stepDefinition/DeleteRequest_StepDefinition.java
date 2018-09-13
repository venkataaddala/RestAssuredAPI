package stepDefinition;

import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.runner.Request;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest_StepDefinition {
	public Response response;
	public ValidatableResponse json;
	public Request request;
    
    public String EndPoint="https://reqres.in";
	
    public RequestSpecification requestspec=RestAssured.given().baseUri(EndPoint);
	
	
	@Given("^Delete url request$")
	public void delete_url_request() {
		requestspec.header("Content-Type", "application/json");
	}

	@When("^user Delete request$")
	public void user_Delete_request() {
		response=requestspec.delete("/api/users/2");
	}

	@Then("^Delete request status code is (\\d+)$")
	public void delete_request_status_code_is(int statusCode) {
		json=response.then().statusCode(statusCode);
	}

	@And("^Delete response headers contains$")
	public void delete_response_headers_contains(Map<String,String> responseFields) {
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
