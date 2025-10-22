package get;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetBoard extends BaseTest {
    @Test
    public void getBoard() {
        given()
                .spec(getReqSpec())
                .when()
                .get("/boards/{id}", "5abbe4b7ddc1b351ef961414")
                .then()
                .spec(getResSpec())
                .body("name", equalTo("Trello Platform Changelog"))
                .body("id", notNullValue());
    }
}
