package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.PostModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.PostSpecs.getResponseSpec;
import static specs.PostSpecs.postRequestSpec;

@Owner("Dina")
@Feature("Проверка функциональности API для управления постами")
public class PostsTests {

    @Test
    @Tag("regress")
    @DisplayName("Получение списка постов")
    public void getPostsTest() {
        String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";

        PostModel[] posts = step("Отправить GET запрос для получения массива постов на эндпоинт '/posts'",
                () -> given(postRequestSpec)
                        .when()
                        .get("/posts")
                        .then()
                        .spec(getResponseSpec(200))
                        .extract().as(PostModel[].class));

        step("Проверить тело ответа", () -> {
            assertEquals(100, posts.length, "Длина массива постов из ответа не равна 100");
            assertEquals(title, posts[0].getTitle(), "Заголовок первого поста из массива не равен '%s'".formatted(title));
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Создание поста")
    public void createPostTest() {
        PostModel body = new PostModel();
        body.setTitle("Преступление и наказание");
        body.setBody("Преступле́ние и наказа́ние — социально-психологический и социально-философский роман Достоевского, над которым писатель работал в 1865—1866 годах.");
        body.setUserId(404);

        PostModel post = step("Отправить POST запрос для создания поста на эндпоинт '/posts'",
                () -> given(postRequestSpec)
                        .body(body)
                        .when()
                        .post("/posts")
                        .then()
                        .spec(getResponseSpec(201))
                        .extract().as(PostModel.class)
        );

        step("Проверить тело ответа", () -> {
            assertEquals(101, post.getId(), "ID созданного поста не равен 101");
            assertEquals(body.getTitle(), post.getTitle(), "Заголовок созданного поста не равен '%s'".formatted(body.getTitle()));
            assertEquals(body.getBody(), post.getBody(), "Содержание созданного поста не равно '%s'".formatted(body.getBody()));
            assertEquals(body.getUserId(), post.getUserId(), "ID автора созданного поста не равен '%s'".formatted(body.getTitle()));
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Получение поста по ID")
    public void getPostByIdTest() {
        int postId = 13;
        int userId = 2;
        String title = "dolorum ut in voluptas mollitia et saepe quo animi";
        String text = "perferendis recusandae assumenda consectetur porro architecto ipsum ipsam";

        PostModel post = step("Отправить GET запрос для получения поста по ID на эндпоинт '/posts/{id}'",
                () -> given(postRequestSpec)
                        .when()
                        .get("/posts/" + postId)
                        .then()
                        .spec(getResponseSpec(200))
                        .extract().as(PostModel.class)
        );

        step("Проверить тело ответа", () -> {
            assertEquals(title, post.getTitle(), "Заголовок поста с ID '%d' не равен '%s'".formatted(postId, title));
            assertTrue(post.getBody().contains(text), "Пост с ID '%d' не содержит '%s'".formatted(postId, text));
            assertEquals(userId, post.getUserId(), "ID автора поста с ID '%d' не равен '%s'".formatted(postId, userId));
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Обновление содержания поста")
    public void updatePostTest() {
        int postId = 3;
        PostModel body = new PostModel();
        body.setTitle("Идиот");
        body.setBody("Идиот — роман писателя Достоевского, впервые опубликованный в номерах журнала «Русский вестник» за 1868 год");
        body.setUserId(404);

        PostModel post = step("Отправить PUT запрос для обновления поста на эндпоинт '/posts/{id}'",
                () -> given(postRequestSpec)
                        .body(body)
                        .when()
                        .put("/posts/" + postId)
                        .then()
                        .spec(getResponseSpec(200))
                        .extract().as(PostModel.class)
        );

        step("Проверить тело ответа", () -> {
            assertEquals(body.getTitle(), post.getTitle(), "Заголовок обновленного поста не равен '%s'".formatted(body.getTitle()));
            assertEquals(body.getBody(), post.getBody(), "Содержание обновленного поста не равно '%s'".formatted(body.getBody()));
            assertEquals(body.getUserId(), post.getUserId(), "ID автора обновленного поста не равен '%s'".formatted(body.getTitle()));
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Частичное обновление содержания поста")
    public void partialUpdatePostTest() {
        int postId = 2;
        PostModel body = new PostModel();
        body.setUserId(404);

        PostModel post = step("Отправить PATCH запрос для частичного обновления поста на эндпоинт '/posts/{id}'",
                () -> given(postRequestSpec)
                        .body(body)
                        .when()
                        .patch("/posts/" + postId)
                        .then()
                        .spec(getResponseSpec(200))
                        .extract().as(PostModel.class)
        );

        step("Проверить тело ответа", () -> {
            assertEquals(body.getUserId(), post.getUserId(), "ID автора обновленного поста не равен '%s'".formatted(body.getTitle()));
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Удаление поста")
    public void deletePostTest() {
        int postId = 18;

        int statusCode = step("Отправить DELETE запрос для удаления поста по ID на эндпоинт '/posts/{id}'",
                () -> given(postRequestSpec)
                        .when()
                        .delete("/posts/" + postId)
                        .then()
                        .extract().statusCode()
        );

        step("Проверить статус-код ответа", () -> {
            assertEquals(200, statusCode, "Статус-код ответа не равен 200");
        });
    }
}
