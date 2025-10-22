package put;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBoard extends BaseTest {
    @Test
    public void updateBoard() {
        String boardId =
                given()
                        .queryParam("name", "TempBoard")
                        .when()
                        .post("/boards/")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        given()
                .queryParam("name", "UpdateBoardName")
                .when()
                .put("/boards/{id}", boardId)
                .then()
                .statusCode(200)
                .body("name", equalTo("UpdateBoardName"))
                .body("id", equalTo(boardId));
    }
}
