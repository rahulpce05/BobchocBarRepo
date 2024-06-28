package com.learn.requests;

import com.learn.reporting.ExtentReportManager;
import com.learn.tests.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class RestClient extends TestBase {

    Response response;
    ExtentReportManager em = new ExtentReportManager();

    /**
     * @param requestSpec
     * @return
     */
    public Response doGetWithRequestSpec(RequestSpecification requestSpec) {

        requestSpec = given()
                .log().all()
                .contentType(ContentType.JSON)
                .when().log().all()
                .spec(requestSpec);
        response = requestSpec.get();
        response.then().log().all().assertThat().statusCode(200);
        printRequestLogInReport(requestSpec);
        printResponseLogInReport(response);

        response.getBody().prettyPrint();
        return response;
    }

    /**
     * @param requestSpec
     * @param
     * @return
     */
    public Response doGetRequestWithPathParam(RequestSpecification requestSpec, String orderId) {

        requestSpec = given().log().all()
                .pathParams("id", orderId)
                .when().log().all()
                .spec(requestSpec);
        response = requestSpec.get();
        response.then().log().all().assertThat().statusCode(200);
        printRequestLogInReport(requestSpec);
        printResponseLogInReport(response);

        response.getBody().prettyPrint();
        return response;
    }

    /**
     * @param requestSpec
     * @param body
     * @return
     */
    public Response doPostRequest(RequestSpecification requestSpec, Object body) {
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);

        requestSpec = given().log().all()
                .contentType(ContentType.JSON)
                .spec(requestSpec)
                .when().log().all()
                .body(body);

        response = requestSpec.post(queryRequest.getBaseUri() + queryRequest.getBasePath());
        response.then().log().all().assertThat().statusCode(201);
        printRequestLogInReport(requestSpec);
        printResponseLogInReport(response);

        response.getBody().prettyPrint();
        return response;
    }

    /**
     * @param
     * @param body
     * @return
     */
    public Response doPutRequest(RequestSpecification requestSpec, String orderId, Object body) {
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);
        Response response = given().log().all()
                .pathParams("id", orderId)
                .contentType(ContentType.JSON)
                .spec(requestSpec)
                .when().log().all()
                .body(body)
                .put(queryRequest.getBaseUri() + queryRequest.getBasePath());
        response.then().log().all().assertThat().statusCode(200);
        return response;
    }

    /**
     * @return
     */
    public Response doDeleteRequest(RequestSpecification requestSpec, String orderId) {

        Response response = given().log().all()
                .pathParams("id", orderId)
                .contentType(ContentType.JSON)
                .spec(requestSpec)
                .when().log().all()
                .delete();

        response.then().log().all().assertThat().statusCode(200);
        return response;
    }


    public Map<String, Object> getResponseMatchDetails(List<HashMap<String, Object>> dataElements, Object searchValue) {
        Map<String, Object> matchingDeviceDetails = null;
        for (Map<String, Object> map : dataElements) {
            if (map.containsValue(searchValue)) {
                matchingDeviceDetails = map;
                break;
            }
        }
        return matchingDeviceDetails;
    }

    public boolean getBoolean(String value) {
        return !value.equals("0");
    }

    public boolean convertToBoolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) ||
                "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
            returnValue = true;
        return returnValue;
    }

        public void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        try {
            em.logInfoDetails("==============Request Details=============================");
            em.logInfoDetails("URI is " + queryableRequestSpecification.getURI());
            em.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
            em.logInfoDetails("Headers are ");
            em.logHeaders(queryableRequestSpecification.getHeaders().asList());
            //em.logInfoDetails("Request body is " + queryableRequestSpecification.getBody());
            em.logInfoDetails("Request body is ");
            em.logJson(queryableRequestSpecification.getBody());
        } catch (NullPointerException e) {

        }
    }

    public void printResponseLogInReport(Response response) {
        try {
            em.logInfoDetails("==============Response Details=============================");
            em.logInfoDetails("Response Status is " + response.getStatusCode());
            em.logInfoDetails("Response Headers are ");
            em.logHeaders(response.getHeaders().asList());
            em.logInfoDetails("Response body is ");
            em.logJson(response.getBody().prettyPrint());
            em.logInfoDetails(response.asString());
            em.logInfoDetails("==============================================================");
        } catch (NullPointerException e) {

        }
    }

    public void ComparisonAssertion(String fieldName, Object expectedValue, Object Actualvalue) {
        em.logInfoDetails("Assertion of field " + "<font color=" + "#7ffaaa>" + fieldName + "</font>" + " completed.<br />" +
                "Expected value is -->" + "<font color=" + "#7ffaaa>" + expectedValue + "</font>" + "<br />" +
                " Actual  value  is --> " + "<font color=" + "#7ffaaa>" + Actualvalue + "</font>");
        Assert.assertEquals(Actualvalue, expectedValue);

    }

    public void boolAssertion(String fieldname, String assertionType, boolean value) {
        if (assertionType.equalsIgnoreCase("True")) {
            Assert.assertTrue(value);
            em.logInfoDetails("Assertion of field " + "<font color=" + "#7ffaaa>" + fieldname + " completed.<br />" +
                    " Expected value is --> " + "<font color=" + "#7ffaaa>" + assertionType + " <br />" +
                    " Actual  value  is --> " + "<font color=" + "#7ffaaa>" + value);
        } else {
            Assert.assertFalse(value);
            em.logInfoDetails("Assertion of field " + "<font color=" + "#7ffaaa>" + fieldname + " completed.<br />" +
                    " Expected value is --> " + "<font color=" + "#7ffaaa>" + assertionType + " <br />" +
                    " Actual  value  is --> " + "<font color=" + "#7ffaaa>" + value);
        }
    }

    public void StatusCodeAssertion(String fieldName, Object expectedValue, int Actualvalue) {

        em.logInfoDetails("Assertion of field " + "<font color=" + "#7ffaaa>" + fieldName + "</font>" + " completed.<br />" +
                "Expected value is -->" + "<font color=" + "#7ffaaa>" + expectedValue + "</font>" + "<br />" +
                " Actual  value  is --> " + "<font color=" + "#7ffaaa>" + Actualvalue + "</font>");
        Assert.assertEquals(Actualvalue, expectedValue);

    }


}