package base;

import utils.ConfigReader;
import utils.EnvReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    protected static RequestSpecification getDefaultRequestSpecification() {
        logger.info("Initializing default request specifications...");

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addQueryParam("key", EnvReader.get("API_KEY"))
                .addQueryParam("token", EnvReader.get("TOKEN"))
                .addFilter(new AllureRestAssured())
                .build();
    }
}