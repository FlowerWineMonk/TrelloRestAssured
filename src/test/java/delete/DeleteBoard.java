package delete;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class DeleteBoard extends BaseTest {
    @Test
    public void deleteBoard() {
        String boardId =
                given()
                        .queryParam("name", "Temp Board")
                        .when()
                        .post("/boards/")
                        .then()
                        .extract()
                        .path("id");

        when()
                .delete("/boards/{id}",boardId)
                .then()
                .statusCode(200);
    }
}
