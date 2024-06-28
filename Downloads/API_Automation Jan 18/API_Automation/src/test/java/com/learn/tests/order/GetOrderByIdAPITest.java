package com.learn.tests.order;

import com.learn.requests.RestClient;
import com.learn.tests.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class GetOrderByIdAPITest extends TestBase {

    RequestSpecBuilder builder = new RequestSpecBuilder();
    RequestSpecification requestSpec;
    ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
    ResponseSpecification responseSpec;
    GetOrderByIdAPI getOrderByIdAPI = new GetOrderByIdAPI();
    String response;
    List<HashMap<String, Object>> keySize;
    String name = "Rahul";
    String email = "rahulpce05@gmail.com";
    int id = 40;
    String orderId = String.valueOf(id);

    @Test
    public void getOrderById() throws Exception {
        responseBuilder.expectStatusCode(200);
        requestSpec = builder.build();
        responseSpec = responseBuilder.build();

        System.out.println("Get Order By Id...");
        response = getOrderByIdAPI.getOrderByIdAPI(builder, requestSpec, responseSpec, orderId);

        keySize = getOrderByIdAPI.getJsonValue(response, "$.[*]");
        if (keySize.isEmpty()) {
            System.out.println("Blank response assertions...");
            Assert.assertEquals(response.toString(), "[\n    \n]");
        } else {
            getOrderByIdAPI.validateResponse(response.toString());
            System.out.println("Response Assertions...");
            Assert.assertEquals(GetOrderByIdAPI.id, id);
            Assert.assertEquals(GetOrderByIdAPI.name, name);
            Assert.assertEquals(GetOrderByIdAPI.email, email);
        }
    }




}
