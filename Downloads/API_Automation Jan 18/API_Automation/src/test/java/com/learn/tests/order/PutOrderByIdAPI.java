package com.learn.tests.order;

import com.learn.requests.RestClient;
import com.learn.tests.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;

public class PutOrderByIdAPI extends TestBase {

    String jResponse;
    RestClient restClient  = new RestClient();
    static int id, typeId, quantity;
    static float price;
    static String name, email, date;
    static boolean isAdult;
    JsonPath js;

    public String updateOrderByIdAPI(RequestSpecBuilder builder, RequestSpecification requestSpec, String orderId, Object payload) {
        builder.setBasePath("/api/Order/{id}");
        requestSpec = builder.build();
        jResponse = restClient.doPutRequest(requestSpec, orderId, payload).asString();
        return jResponse;
    }

    public List<HashMap<String, Object>> getJsonValue(String jsonResponse, String path) {
        dataElements = com.jayway.jsonpath.JsonPath.read(jsonResponse, path);
        System.out.println("Number of keys ==> " + dataElements.size());
        return dataElements;
    }

    public void validateResponse(String jResponse) {
        js = JsonPath.from(jResponse);
        isAdult = js.get("IsAdult");
        System.out.println("IsAdult: " + isAdult);
        id = js.get("Id");
        System.out.println("Id: " + id);
        typeId = js.get("TypeId");
        System.out.println("TypeId: " + typeId);
        quantity = js.get("Quantity");
        System.out.println("Quantity: " + quantity);
        name = js.get("Name");
        System.out.println("Name: " + name);
        email = js.get("Email");
        System.out.println("Email: " + email);
        price = js.get("Price");
        System.out.println("Price: " + price);
        date = js.get("Date");
        System.out.println("Date: " + date);
    }
}
