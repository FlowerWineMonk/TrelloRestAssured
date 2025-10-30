package delete;

import base.BaseTest;
import enums.BoardEndpoints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;

public class DeleteBoard extends BaseTest {
    private static final Logger logger = LogManager.getLogger(DeleteBoard.class);

    @Test
    public void deleteBoard() {
        logger.info("Starting DeleteBoard test...");

        String tempBoardName = "Temp Board For Deletion";
        logger.debug("Creating temporary board: {}", tempBoardName);

        String boardId =
                given()
                        .spec(getDefaultRequestSpecification())
                        .queryParam("name", tempBoardName)
                        .when()
                        .post(BoardEndpoints.CREATE_BOARD.getEndpoint())
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        logger.debug("Temporary board created. ID: {}", boardId);
        logger.debug("Deleting board with ID: {}", boardId);

        given()
                .spec(getDefaultRequestSpecification())
                .when()
                .delete(BoardEndpoints.DELETE_BOARD.getEndpoint(), boardId)
                .then()
                .statusCode(200)
                .body("id", nullValue());

        logger.info("Board deleted successfully. ID: {}", boardId);
    }
}