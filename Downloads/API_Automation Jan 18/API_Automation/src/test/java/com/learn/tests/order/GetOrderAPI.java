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

public class GetOrderAPI extends TestBase{

    RestClient restClient = new RestClient();
    JsonPath jsonPath;
    static List<Object> list = new ArrayList<>();
    String jResponse;


    public String getOrderAPI(RequestSpecBuilder builder, RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        builder.setBasePath("/api/Order");
        requestSpec = builder.build();
        jResponse = restClient.doGetWithRequestSpec(requestSpec).asString();
        return jResponse;
    }

    public int getOrderCount(String jResponse){
        jsonPath = JsonPath.from(jResponse);
        list = jsonPath.get("$");
        System.out.println("getOrderCount : " +list.size());
        return list.size();
    }

    public static Object getJsonElementExists(String res, String queryKey, String queryvalue, String inputkey) throws Exception {
        String jp1;
        Set<String> finalKeys = new HashSet<>();
        JsonPath jp = new JsonPath(res);
        List<Object> str1 = jp.getList("findAll{it." + queryKey + " == '" + queryvalue + "'}");

        ObjectMapper omap = new ObjectMapper();
        System.out.println("All keys & values are : \n");
        for(int i=0; i<str1.size(); i++){
            jp1 = omap.writeValueAsString(str1.get(i));
            System.out.println(jp1+ "\n");

            findAllKeys(new JSONObject(jp1),null, finalKeys);
            System.out.println("Final keys are: \n" + finalKeys);
        }
        return finalKeys.contains(inputkey);
    }

    public static void findAllKeys(Object object, String key, Set<String> finalKeys) {
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            jsonObject.keySet().forEach(childKey -> {
                findAllKeys(jsonObject.get(childKey), key != null ? key + "." + childKey : childKey, finalKeys);
            });

        }else if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            finalKeys.add(key);
            IntStream.range(0, jsonArray.length())
                    .mapToObj(jsonArray::get)
                    .forEach(jsonObject -> findAllKeys(jsonObject, key, finalKeys));
        }else {
            finalKeys.add(key);
        }
    }


}
