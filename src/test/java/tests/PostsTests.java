package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Owner("Dina")
@Story("Проверка функциональности API для управления постами")
public class PostsTests extends BaseTest {

    @Test
    @DisplayName("Получение списка постов")
    public void getPostsTest() {
        String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";

        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("posts.size()", is(100))
                .body("[0].title", equalTo(title));
    }

    @Test
    @DisplayName("Создание поста")
    public void createPostTest() {
        String title = "Преступление и наказание";
        String body = "Преступле́ние и наказа́ние — социально-психологический и социально-философский роман Достоевского, над которым писатель работал в 1865—1866 годах.";
        String postBody = """
                {
                  "title": "%s",
                  "body": "%s",
                  "userId": 404
                }
                """.formatted(title, body);

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(postBody)
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("id", is(101))
                .body("title", equalTo(title))
                .body("body", equalTo(body))
                .body("userId", is(404));
    }

    @Test
    @DisplayName("Получение поста по ID")
    public void getPostByIdTest() {
        int postId = 13;
        String title = "dolorum ut in voluptas mollitia et saepe quo animi";
        String text = "perferendis recusandae assumenda consectetur";

        given()
                .spec(requestSpec)
                .pathParam("id", postId)
                .when()
                .get("/posts/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .body("userId", is(2))
                .body("title", equalTo(title))
                .body("body", containsString(text));
    }

    @Test
    @DisplayName("Обновление содержания поста")
    public void updatePostTest() {
        int postId = 3;
        String title = "Идиот";
        String body = "Идиот — роман писателя Достоевского, впервые опубликованный в номерах журнала «Русский вестник» за 1868 год";
        String postBody = """
                {
                  "title": "%s",
                  "body": "%s",
                  "userId": 404
                }
                """.formatted(title, body);

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .pathParam("id", postId)
                .body(postBody)
                .when()
                .put("/posts/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .body("id", is(postId))
                .body("title", equalTo(title))
                .body("body", equalTo(body))
                .body("userId", is(404));
    }

    @Test
    @DisplayName("Частичное обновление содержания поста")
    public void partialUpdatePostTest() {
        int postId = 2;
        String postBody = """
                {
                  "userId": 404
                }
                """;

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .pathParam("id", postId)
                .body(postBody)
                .when()
                .patch("/posts/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .body("id", is(postId))
                .body("userId", is(404));
    }

    @Test
    @DisplayName("Удаление поста")
    public void deletePostTest() {
        int postId = 18;

        given()
                .spec(requestSpec)
                .pathParam("id", postId)
                .when()
                .delete("/posts/{id}")
                .then()
                .statusCode(200);
    }
}
