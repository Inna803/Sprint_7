package ru.yandex.praktikum;

import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.apache.http.HttpStatus.SC_OK;

@Story("Список заказов курьера")
public class OrderListTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        // инициализируем объект orderClient перед тестом
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Получить список заказов клиента")
    public void getOrderList() {
        // выполняем запрос на получение списка заказов
        var listOfOrdersResponse = orderClient.getOrderlist();

        // извлекаем статус-код ответа
        int statusCode = listOfOrdersResponse.extract().statusCode();

        // извлекаем количество заказов из ответа
        int ordersCount = listOfOrdersResponse.extract().path("pageInfo.total");

        // извлекаем идентификаторы заказов из ответа
        ArrayList<Integer> ordersId = listOfOrdersResponse.extract().path("orders.id");

        // извлекаем имена доступных станций из ответа
        ArrayList<String> availableStationsNames = listOfOrdersResponse.extract().path("availableStations.name");

        // проверяем, что статус-код соответствует SC_OK (коду успешного выполнения)
        Assert.assertEquals(SC_OK, statusCode);

        // проверяем, что количество заказов не равно нулю
        Assert.assertNotEquals(0, ordersCount);

        // проверяем, что список идентификаторов заказов не пустой
        Assert.assertNotEquals(0, ordersId.size());

        // проверяем, что список имен доступных станций не пустой
        Assert.assertNotEquals(0, availableStationsNames.size());
    }
}