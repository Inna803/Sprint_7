package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {
    private static final String COURIER_PATH = "api/v1/courier/"; // путь к ресурсу "курьер"

    // аннотация для отображения в отчёте фреймворка Allure
    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .spec(getBaseSpec()) // используется базовая спецификация запроса
                .body(courier) // устанавливается тело запроса с переданным объектом Courier
                .when()
                .post(COURIER_PATH) // выполняется POST-запрос по указанному пути
                .then().log().all(); // логируются все детали ответа
    }

    // аннотация для отображения в отчёте Allure
    @Step("Вход по логину в систему с валидными данными")
    public int login(CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec()) // используется базовая спецификация запроса
                .body(credentials) // устанавливается тело запроса с переданными учетными данными CourierCredentials
                .when()
                .post(COURIER_PATH + "login") // выполняется POST-запрос по указанному пути для входа в систему
                .then().log().all() // логируются все детали ответа
                .assertThat()
                .statusCode(200) // проверяется код состояния ответа
                .extract()
                .path("id"); // извлекается значение поля "id" из ответа
    }

    // аннотация для отображения в отчёте Allure
    @Step("Вход по логину с невалидными данными")
    public ValidatableResponse loginIsNotValid(CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec()) // используется базовая спецификация запроса
                .body(credentials) // устанавливается тело запроса с переданными учетными данными CourierCredentials
                .when()
                .post(COURIER_PATH + "login") // выполняется POST-запрос по указанному пути для входа в систему
                .then(); // возвращается ValidatableResponse для последующей проверки ответа
    }

    // аннотация для отображения в отчёте Allure
    @Step("Удаление курьера")
    public boolean delete(int id) {
        return given().log().all() // логируются все детали запроса
                .spec(getBaseSpec()) // используется базовая спецификация запроса
                .when()
                .delete(COURIER_PATH + id) // выполняется DELETE-запрос по указанному пути и идентификатору курьера
                .then().log().all() // логируются все детали ответа
                .assertThat()
                .statusCode(200) // проверяется код состояния ответа
                .extract()
                .path("ok"); // извлекается значение поля "ok" из ответа
    }
}