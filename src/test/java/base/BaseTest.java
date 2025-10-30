package base;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import utils.ConfigReader;
import utils.EnvReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

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

    @AfterEach
    public void attachLogs() throws IOException {
        try (FileInputStream fis = new FileInputStream("logs/test-log.log")) {
            Allure.addAttachment("Execution Logs", "text/plain", fis, ".log");
        }
    }
}