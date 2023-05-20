package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Story("Класс создания курьера и входа в систему с валидными учетными данными")
public class CreatingACourierToLogInValidDataTest {
    private CourierClient courierClient; // клиент для взаимодействия с курьерами
    private Courier courier; // созданный курьер
    private int courierId; // идентификатор курьера

    /**
     * Метод, выполняющийся перед каждым тестом.
     * Создает нового курьера с помощью рандомного генератора и сохраняет его в базе данных.
     */
    @Before
    public void setUp() {
        courier = Courier.getRandom(); // генерация случайного курьера
        courierClient = new CourierClient(); // инициализация клиента для взаимодействия с курьерами
        courierClient.createCourier(courier); // создание курьера
    }

    /**
     * Метод, выполняющийся после каждого теста.
     * Удаляет созданного курьера из базы данных.
     */
    @After
    public void tearDown() {
        courierClient.delete(courierId); // удаление курьера
    }

    /**
     * Тестовый метод для проверки создания курьера с валидными учетными данными.
     * Создает нового курьера с помощью рандомного генератора и выполняет проверки на код ответа, успешное создание и идентификатор курьера.
     */
    @Test
    @Description("С помощью рандомного генератора был создан курьер с валидными данными")
    @DisplayName("Создание курьера с валидными учетными данными")
    public void creatingCourierWithValidData() {
        Courier courier = Courier.getRandom(); // генерация случайного курьера
        var createResponse = courierClient.createCourier(courier); // создание курьера
        boolean isCreated = createResponse.extract().path("ok"); // проверка успешного создания курьера
        int statusCode = createResponse.extract().statusCode(); // извлечение кода ответа
        courierId = courierClient.login(CourierCredentials.from(courier)); // вход курьера в систему и получение идентификатора

        Assert.assertEquals(SC_CREATED, statusCode); // проверка, что код ответа равен 201 (создано)
        Assert.assertTrue(isCreated); // проверка успешного создания курьера
        Assert.assertNotEquals(0, courierId); // проверка, что идентификатор курьера не равен 0
    }

    /**
     * Тестовый метод для проверки входа курьера в систему с валидными учетными данными.
     * Выполняет вход курьера в систему с помощью рандомного генератора и выполняет проверки на код ответа и идентификатор курьера.
     */
    @Test
    @Description("Вход курьера в систему с валидными учетными данными с помощью рандомного генератора")
    @DisplayName("Вход курьера в систему с валидными учетными данными")
    public void logInWithValidCredentials() {
        courierId = courierClient.login(CourierCredentials.from(courier)); // вход курьера в систему и получение идентификатора

        assertThat(SC_OK, equalTo(SC_OK)); // проверка, что код ответа равен 200 (ОК)
        Assert.assertNotEquals(0, courierId); // проверка, что идентификатор курьера не равен 0
    }
}