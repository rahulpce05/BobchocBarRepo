package com.learn.tests.order;

import com.learn.tests.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetOrderAPITest extends TestBase{

    RequestSpecBuilder builder = new RequestSpecBuilder();
    RequestSpecification requestSpec;
    ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
    ResponseSpecification responseSpec;
    GetOrderAPI orderAPI = new GetOrderAPI();
    Object objJsonElement;
    String response;
    int size;
    String key = "Name";
    String value = "Rahul";
    String inputKey = "Email";

    @BeforeMethod
    public  void initialize(){
        builder = new RequestSpecBuilder();
    }

    @Test
    public void getAllOrders() throws Exception {
        responseBuilder.expectStatusCode(200);
        requestSpec = builder.build();
        responseSpec = responseBuilder.build();

        System.out.println("Get All Orders...");
        response = orderAPI.getOrderAPI(builder, requestSpec, responseSpec);

        size = orderAPI.getOrderCount(response);
        if (size == 0) {
            System.out.println("Blank response assertions...");
            Assert.assertEquals(response.toString(), "[\n    \n]");
        } else {
            objJsonElement = orderAPI.getJsonElementExists(response, key, value, inputKey);
            System.out.println("objJsonElement validation : " + objJsonElement);
            Assert.assertEquals(objJsonElement, true);
        }
    }




}
