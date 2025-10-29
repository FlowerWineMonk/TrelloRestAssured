package put;

import base.BaseTest;
import enums.BoardEndpoints;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBoard extends BaseTest {
    @Test
    public void updateBoard() {
        String boardId =
                given()
                        .spec(getDefaultRequestSpecification())
                        .queryParam("name", "Temp Board For Update")
                        .when()
                        .post(BoardEndpoints.CREATE_BOARD.getEndpoint())
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        given()
                .spec(getDefaultRequestSpecification())
                .queryParam("name", "UpdateBoardName")
                .when()
                .put(BoardEndpoints.UPDATE_BOARD.getEndpoint(), boardId)
                .then()
                .statusCode(200)
                .body("name", equalTo("UpdateBoardName"))
                .body("id", equalTo(boardId));
    }
}
