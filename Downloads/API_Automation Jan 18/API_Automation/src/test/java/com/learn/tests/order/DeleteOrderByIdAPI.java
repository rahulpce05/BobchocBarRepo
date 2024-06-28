package com.learn.tests.order;

import com.learn.requests.RestClient;
import com.learn.tests.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class DeleteOrderByIdAPI extends TestBase {

    String jResponse;
    RestClient restClient  = new RestClient();

    public String deleteOrderByIdAPI(RequestSpecBuilder builder, RequestSpecification requestSpec, String orderId) {
        builder.setBasePath("/api/Order/{id}");
        requestSpec = builder.build();
        jResponse = restClient.doDeleteRequest(requestSpec, orderId).asString();
        return jResponse;
    }
}
