package ann.blin.api;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static ann.blin.filter.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;

public class Specs {

    public static RequestSpecification request = with().filter(customLogFilter().withCustomTemplates()).contentType(ContentType.JSON).basePath("/api");

    public static ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200).build();
}

