package com.learn.tests;

import com.learn.util.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import java.util.HashMap;
import java.util.List;

public class TestBase {
	public static PropertyReader prop;
	public List<HashMap<String, Object>> dataElements;

	public TestBase()
	{
		initUrl();
	}
	
 @BeforeClass
 	public static void initUrl() {
		prop = PropertyReader.getInstance();
		if(prop.getProperty("environment").equalsIgnoreCase("test"))
		{
			RestAssured.baseURI = prop.getProperty("testbaseurl");
		}
	}
}
