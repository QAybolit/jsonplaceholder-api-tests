package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomAllureListener.withCustomTemplates;

public class BaseTest {

    public static RequestSpecification requestSpec;

    @BeforeAll
    public static void setUp() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setAccept(ContentType.JSON)
                .addFilter(withCustomTemplates());

        requestSpec = builder.build();
    }
}
