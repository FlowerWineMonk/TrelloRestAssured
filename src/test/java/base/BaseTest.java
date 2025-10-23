package base;

import configLoader.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {
        private static final RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.get("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addQueryParam("key", ConfigLoader.get("API_KEY"))
                .addQueryParam("token", ConfigLoader.get("TOKEN"))
                .addFilter(new AllureRestAssured())
                .build();

        private static final ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        protected static RequestSpecification getReqSpec() {
            return reqSpec;
        }

        protected static ResponseSpecification getResSpec() {
            return resSpec;
        }
}