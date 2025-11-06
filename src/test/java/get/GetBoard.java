package get;

import base.BaseTest;
import enums.BoardEndpoints;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetBoard extends BaseTest {
    @Test
    public void getBoard() {
        given()
                .spec(getDefaultRequestSpecification())
                .when()
                .get(BoardEndpoints.GET_BOARD.getEndpoint(), "5abbe4b7ddc1b351ef961414")
                .then()
                .statusCode(200)
                .body("name", equalTo("Trello Platform Changelog"))
                .body("id", notNullValue());
    }
}