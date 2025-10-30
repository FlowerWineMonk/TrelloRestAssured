package put;

import base.BaseTest;
import enums.BoardEndpoints;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBoard extends BaseTest {
    private static final Logger logger = LogManager.getLogger(UpdateBoard.class);

    @Test
    public void updateBoard() {
        logger.info("Starting UpdateBoard test...");

        String initialName = "Temp Board For Update";
        logger.debug("Creating a temporary board: {}", initialName);

        String boardId =
                given()
                        .spec(getDefaultRequestSpecification())
                        .queryParam("name", initialName)
                        .when()
                        .post(BoardEndpoints.CREATE_BOARD.getEndpoint())
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        logger.debug("Temporary board created. ID: {}", boardId);

        String updatedName = "UpdateBoardName";
        logger.debug("Updating board '{}' (ID: {}) to new name '{}'", initialName, boardId, updatedName);

        given()
                .spec(getDefaultRequestSpecification())
                .queryParam("name", updatedName)
                .when()
                .put(BoardEndpoints.UPDATE_BOARD.getEndpoint(), boardId)
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedName))
                .body("id", equalTo(boardId));

        logger.info("Board updated successfully: {} → {}", initialName, updatedName);
    }
}
