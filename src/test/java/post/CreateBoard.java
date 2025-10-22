package post;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBoard extends BaseTest {
    @Test
    public void createBoard() {
        given()
                .queryParam("name", "AutomateCreateBoard")
                .when()
                .post("/boards/")
                .then()
                .statusCode(200)
                .body("name", equalTo("AutomateCreateBoard"))
                .body("id", notNullValue());
    }
}
