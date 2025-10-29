package delete;

import base.BaseTest;
import enums.BoardEndpoints;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;

public class DeleteBoard extends BaseTest {
    @Test
    public void deleteBoard() {
        String boardId =
                given()
                        .spec(getDefaultRequestSpecification())
                        .queryParam("name", "Temp Board For Deletion")
                        .when()
                        .post(BoardEndpoints.CREATE_BOARD.getEndpoint())
                        .then()
                        .extract()
                        .path("id");

        given()
                .spec(getDefaultRequestSpecification())
                .when()
                .delete(BoardEndpoints.DELETE_BOARD.getEndpoint(), boardId)
                .then()
                .statusCode(200)
                .body("id", nullValue());
    }
}
