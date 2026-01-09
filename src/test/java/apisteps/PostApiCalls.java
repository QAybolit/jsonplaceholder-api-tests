package apisteps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.PostModel;

import static io.restassured.RestAssured.given;
import static specs.PostSpecs.getResponseSpec;
import static specs.PostSpecs.postRequestSpec;

public class PostApiCalls {

    @Step("Отправить запрос POST /posts")
    public static ValidatableResponse createPost(PostModel body, int statusCode) {
        return given(postRequestSpec)
                .body(body)
                .when()
                .post("/posts")
                .then()
                .spec(getResponseSpec(statusCode));
    }

    @Step("Отправить запрос GET /posts")
    public static ValidatableResponse getPosts(int statusCode) {
        return given(postRequestSpec)
                .when()
                .get("/posts")
                .then()
                .spec(getResponseSpec(statusCode));
    }

    @Step("Отправить запрос GET /posts/{id}")
    public static ValidatableResponse getPostById(int postId, int statusCode) {
        return given(postRequestSpec)
                .when()
                .get("/posts/" + postId)
                .then()
                .spec(getResponseSpec(statusCode));
    }

    @Step("Отправить запрос PUT /posts/{id}")
    public static ValidatableResponse updatePost(PostModel body, int postId, int statusCode) {
        return given(postRequestSpec)
                .body(body)
                .when()
                .put("/posts/" + postId)
                .then()
                .spec(getResponseSpec(statusCode));
    }

    @Step("Отправить запрос PATCH /posts/{id}")
    public static ValidatableResponse partialUpdatePost(PostModel body, int postId, int statusCode) {
        return given(postRequestSpec)
                .body(body)
                .when()
                .patch("/posts/" + postId)
                .then()
                .spec(getResponseSpec(statusCode));
    }

    @Step("Отправить запрос DELETE /posts/{id}")
    public static ValidatableResponse deletePost(int postId) {
        return given(postRequestSpec)
                .when()
                .delete("/posts/" + postId)
                .then();
    }
}
