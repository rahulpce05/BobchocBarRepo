package com.learn.tests.order;

import com.learn.datareader.JsonReader;
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

public class PutOrderByIdAPITest extends TestBase {

    RequestSpecBuilder builder = new RequestSpecBuilder();
    RequestSpecification requestSpec;
    ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
    ResponseSpecification responseSpec;
    PutOrderByIdAPI putOrderByIdAPI = new PutOrderByIdAPI();
    List<HashMap<String, Object>> keySize;
    String response, reqPayload;
    String name = "Rahul";
    String email = "rahulpce05@gmail.com";
    int id = 15;
    String orderId = String.valueOf(id);

    @BeforeMethod
    public  void initialize(){
        builder = new RequestSpecBuilder();
    }

    @Test
    public void updateOrderById() throws Exception {
        responseBuilder.expectStatusCode(200);
        requestSpec = builder.build();
        responseSpec = responseBuilder.build();

        System.out.println("Update an Order...");
        reqPayload = JsonReader.generateStringFromResource(System.getProperty("user.dir") + prop.getProperty("putOrderAPIPayload"));
        response = putOrderByIdAPI.updateOrderByIdAPI(builder, requestSpec, orderId, reqPayload);

        keySize = putOrderByIdAPI.getJsonValue(response, "$.[*]");
        if (keySize.isEmpty()) {
            System.out.println("Blank response assertions...");
            Assert.assertEquals(response, "[\n    \n]");
        } else {
            putOrderByIdAPI.validateResponse(response);
            System.out.println("Response Assertions...");
            Assert.assertEquals(PostOrderAPI.isAdult, true);
            Assert.assertEquals(PostOrderAPI.name, name);
            Assert.assertEquals(PostOrderAPI.email, email);
        }
    }
}
