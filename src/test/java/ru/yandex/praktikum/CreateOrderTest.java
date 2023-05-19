package ru.yandex.praktikum;

import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;

@Story("Создание заказа пользователем")
@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderClient orderClient; // клиент для взаимодействия с заказами
    private Order order; // созданный заказ
    private Integer track; // номер созданного заказа
    private final List<String> scooterColors; // список цветов скутеров

    /**
     * Конструктор класса.
     *
     * @param scooterColors строка, представляющая список цветов скутеров.
     */
    public CreateOrderTest(String scooterColors) {
        this.scooterColors = List.of(scooterColors);
    }

    /**
     * Метод для получения параметров теста.
     *
     * @return массив параметров, представляющих список цветов скутеров.
     */
    @Parameterized.Parameters
    public static Object[][] getColors() {
        return new Object[][]{
                {"\"BLACK\", \"GREY\""}, // список цветов: черный и серый
                {"\"GREY\""}, // список цветов: серый
                {"\"BLACK\""}, // список цветов: черный
                {""} // пустой список цветов
        };
    }

    /**
     * Метод, выполняющийся перед каждым тестом. Инициализирует клиент для взаимодействия с заказами и создает новый объект Order.
     */
    @Before
    public void setUp() {
        orderClient = new OrderClient();
        order = new Order(scooterColors);
    }

    /**
     * Метод, выполняющийся после каждого теста. Отменяет созданный заказ.
     */
    @After
    public void tearDown() {
        orderClient.cancelOrder(track.toString());
    }

    /**
     * Тестовый метод для проверки создания заказа.
     * Создает новый заказ и выполняет проверки на код ответа и номер заказа.
     */
    @Test
    @DisplayName("Создание ордера")
    public void createOrder() {
        var createResponse = orderClient.createOrder(order); // создание заказа
        int statusCode = createResponse.extract().statusCode(); // извлечение кода ответа
        track = createResponse.extract().path("track"); // извлечение номера заказа

        Assert.assertEquals(SC_CREATED, statusCode); // проверка, что код ответа равен 201 (создано)
        Assert.assertNotEquals(0, track.intValue()); // проверка, что номер заказа не равен 0
    }
}