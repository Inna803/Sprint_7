package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {
    private static final String ORDER_PATH = "api/v1/orders/";

    /**
     * Метод для создания заказа.
     *
     * @param order объект Order, представляющий создаваемый заказ.
     * @return ValidatableResponse, объект для дальнейшей проверки и ассертов.
     */
    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getBaseSpec()) // устанавливается базовая спецификация для запроса
                .body(order) // устанавливается тело запроса с переданным объектом Order
                .when()
                .post(ORDER_PATH) // выполняется POST-запрос по указанному пути
                .then(); // возвращает объект ValidatableResponse для дальнейшей проверки и ассертов
    }

    /**
     * Метод для отмены заказа.
     *
     * @param track номер заказа для отмены.
     * @return ValidatableResponse, объект для дальнейшей проверки и ассертов.
     */
    @Step("Отменить заказ")
    public ValidatableResponse cancelOrder(String track) {
        return given()
                .spec(getBaseSpec()) // устанавливается базовая спецификация для запроса
                .body(track) // устанавливается тело запроса с переданным номером заказа
                .when()
                .put(ORDER_PATH + "cancel") // выполняется PUT-запрос по указанному пути для отмены заказа
                .then(); // возвращает объект ValidatableResponse для дальнейшей проверки и ассертов
    }

    /**
     * Метод для получения списка заказов.
     *
     * @return ValidatableResponse, объект для дальнейшей проверки и ассертов.
     */
    @Step("Получение списка заказов")
    public ValidatableResponse getOrderlist() {
        return given()
                .spec(getBaseSpec()) // устанавливается базовая спецификация для запроса
                .when()
                .get(ORDER_PATH) // выполняется GET-запрос по указанному пути для получения списка заказов
                .then(); // возвращает объект ValidatableResponse для дальнейшей проверки и ассертов
    }
}