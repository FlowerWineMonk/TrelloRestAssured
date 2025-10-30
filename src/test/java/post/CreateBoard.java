package post;

import base.BaseTest;
import enums.BoardEndpoints;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBoard extends BaseTest {
    private static final Logger logger = LogManager.getLogger(CreateBoard.class);

    @Test
    public void createBoard() {
        String boardName = "AutomateCreateBoard";
        logger.info("Starting CreateBoard test...");
        logger.debug("Attempting to create a board with name: {}", boardName);

        String boardId =
                given()
                        .spec(getDefaultRequestSpecification())
                        .queryParam("name", "AutomateCreateBoard")
                        .when()
                        .post(BoardEndpoints.CREATE_BOARD.getEndpoint())
                        .then()
                        .statusCode(200)
                        .body("name", equalTo("AutomateCreateBoard"))
                        .body("id", notNullValue())
                        .extract()
                        .path("id");;

        logger.info("Board created successfully. Name: {}, ID: {}", boardName, boardId);
    }
}
