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
                        .spec(getReqSpec())
                        .queryParam("name", "Temp Board For Update")
                        .when()
                        .post("/boards/")
                        .then()
                        .spec(getResSpec())
                        .extract()
                        .path("id");

        given()
                .spec(getReqSpec())
                .queryParam("name", "UpdateBoardName")
                .when()
                .put("/boards/{id}", boardId)
                .then()
                .spec(getResSpec())
                .body("name", equalTo("UpdateBoardName"))
                .body("id", equalTo(boardId));
    }
}
