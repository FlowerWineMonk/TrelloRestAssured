package delete;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBoard extends BaseTest {
    @Test
    public void deleteBoard() {
        String boardId =
                given()
                        .spec(getReqSpec())
                        .queryParam("name", "Temp Board For Deletion")
                        .when()
                        .post("/boards/")
                        .then()
                        .extract()
                        .path("id");

        given()
                .spec(getReqSpec())
                .when()
                .delete("/boards/{id}",boardId)
                .then()
                .spec(getResSpec());
    }
}
