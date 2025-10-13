package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class PostSpecs {

    public static RequestSpecification postRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://jsonplaceholder.typicode.com")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .log().all();

    public static ResponseSpecification getResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(statusCode)
                .build();
    }
}
