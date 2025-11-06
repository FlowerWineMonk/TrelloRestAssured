package base;

import utils.ConfigReader;
import utils.EnvReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
  protected static RequestSpecification getDefaultRequestSpecification() {
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