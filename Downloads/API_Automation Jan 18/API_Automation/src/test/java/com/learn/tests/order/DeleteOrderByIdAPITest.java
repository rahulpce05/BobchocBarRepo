package com.learn.tests.order;

import com.learn.tests.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteOrderByIdAPITest extends TestBase {

    RequestSpecBuilder builder = new RequestSpecBuilder();
    RequestSpecification requestSpec;
    ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
    ResponseSpecification responseSpec;
    DeleteOrderByIdAPI deleteOrderByIdAPI = new DeleteOrderByIdAPI();
    String response, reqPayload;
    int id = 17;
    String orderId = String.valueOf(id);

    @BeforeMethod
    public  void initialize(){
        builder = new RequestSpecBuilder();
    }

    @Test
    public void deleteOrderById() throws Exception {
        responseBuilder.expectStatusCode(200);
        requestSpec = builder.build();
        responseSpec = responseBuilder.build();

        System.out.println("Delete an Order...");
        response = deleteOrderByIdAPI.deleteOrderByIdAPI(builder, requestSpec, orderId);
        System.out.println(response);
        }
    }

