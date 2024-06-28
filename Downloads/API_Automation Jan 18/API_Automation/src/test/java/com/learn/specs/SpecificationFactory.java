package com.learn.specs;

import com.learn.tests.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;

public class SpecificationFactory extends TestBase {
	public static synchronized ResponseSpecification getGenericResponseSpec() {
		ResponseSpecBuilder responseSpec;
		ResponseSpecification responseSpecification;
		responseSpec = new ResponseSpecBuilder();
		responseSpec.expectHeader("Content-Type","application/json; charset=utf-8");
		//responseSpec.expectHeader("Transfer-Encoding","null");
		responseSpec.expectResponseTime(lessThan(25L), TimeUnit.SECONDS);
		responseSpecification = responseSpec.build();
		return responseSpecification;
	}

	public static synchronized RequestSpecification logPayloadResponseInfo() {
		RequestSpecBuilder logBuilder;
		RequestSpecification logSpecification;
		logBuilder = new RequestSpecBuilder();
		
		if(prop.getProperty("log").equals("ENABLE")) {
			//logBuilder.addFilter(new AllureRestAssured());
		}
		logSpecification = logBuilder.build();
		return logSpecification;
	}
}
