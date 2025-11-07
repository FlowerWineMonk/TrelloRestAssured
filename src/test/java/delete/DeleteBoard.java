package delete;

import base.BaseTest;
import enums.BoardEndpoints;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void deleteAllBoards() {
        List<String> boardIds =
                given()
                        .spec(getDefaultRequestSpecification())
                        .when()
                        .get("/members/me/boards")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        System.out.println("Found " + boardIds.size() + " boards. Deleting...");

        if (boardIds == null || boardIds.isEmpty()) {
            System.out.println("There are no boards. Batch deletion will be skipped!");
        } else {
            for (String boardId : boardIds) {
                given()
                        .spec(getDefaultRequestSpecification())
                        .when()
                        .delete(BoardEndpoints.DELETE_BOARD.getEndpoint(), boardId)
                        .then()
                        .statusCode(200);

                System.out.println("Deleted: " + boardId);
            }
            System.out.println("✅ All boards deleted.");
        }
    }
}
