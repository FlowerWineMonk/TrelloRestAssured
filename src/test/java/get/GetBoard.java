package get;

import base.BaseTest;
import enums.BoardEndpoints;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetBoard extends BaseTest {
    private static final Logger logger = LogManager.getLogger(GetBoard.class);

    @Test
    public void getBoard() {
        logger.info("Starting GetBoard test...");
        String boardId = "5abbe4b7ddc1b351ef961414";
        logger.debug("Requesting board details for ID: {}", boardId);

        given()
                .spec(getDefaultRequestSpecification())
                .when()
                .get(BoardEndpoints.GET_BOARD.getEndpoint(), boardId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Trello Platform Changelog"))
                .body("id", notNullValue());

        logger.info("Successfully validated board details for ID: {}", boardId);
    }
}