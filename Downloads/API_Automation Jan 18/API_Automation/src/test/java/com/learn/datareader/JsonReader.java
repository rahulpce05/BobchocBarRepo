package com.learn.datareader;

import com.jayway.jsonpath.JsonPath;
import org.aspectj.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
public class JsonReader {
    public static List<HashMap<String, Object>> dataElements;

    public static List<HashMap<String, Object>> getExpectedJsonTestData(String jsonDataFilePath) throws IOException {
        String expectedJsonData = FileUtil.readAsString(new File(jsonDataFilePath));
        dataElements = getJsonValue(expectedJsonData, "$.[*]");
        return dataElements;
    }

    public static List<HashMap<String, Object>> getJsonValue(String jsonResponse, String path) {
        dataElements = JsonPath.read(jsonResponse,path);
        System.out.println("Number of keys ==> " + dataElements.size());
        return dataElements;
    }

    public static String generateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));

    }
}
