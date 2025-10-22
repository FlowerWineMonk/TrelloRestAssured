package base;

import configLoader.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected static RequestSpecification reqSpec;
    protected static final int OK_STATUS_CODE = 200;

    @BeforeAll
    public static void setup() {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.get("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addQueryParam("key", ConfigLoader.get("API_KEY"))
                .addQueryParam("token", ConfigLoader.get("TOKEN"))
                .build();

        RestAssured.requestSpecification = reqSpec;
    }
}