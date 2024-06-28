package com.learn.tests.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.requests.RestClient;
import com.learn.tests.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.IntStream;

public class GetOrderByIdAPI extends TestBase {
    String jResponse;
    RestClient restClient = new RestClient();
    static int id, typeId, quantity;
    static float price;
    static String name, email, date;
    JsonPath js;

    public String getOrderByIdAPI(RequestSpecBuilder builder, RequestSpecification requestSpec, ResponseSpecification responseSpec, String orderId) {
        builder.setBasePath("/api/Order/{id}");
        requestSpec = builder.build();
        jResponse = restClient.doGetRequestWithPathParam(requestSpec, orderId).asString();
        return jResponse;
    }

    public List<HashMap<String, Object>> getJsonValue(String jsonResponse, String path) {
        dataElements = com.jayway.jsonpath.JsonPath.read(jsonResponse, path);
        System.out.println("Number of keys ==> " + dataElements.size());
        return dataElements;
    }

    public void validateResponse(String jResponse) {
        js = JsonPath.from(jResponse);
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