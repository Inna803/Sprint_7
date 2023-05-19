package ru.yandex.praktikum;


import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;


// аннотация для определения истории (Story) данного класса
@Story("Создание курьера с не валидными учетными данными")
public class CreatingCourierAndLoggingIinWithInvalidDataTest {

    // переменные класса
    private CourierClient courierClient; // объект для взаимодействия с API курьера
    private Courier courier; // объект курьера
    private int statusCode; // переменная для хранения статус-кода

    // метод, выполняющийся перед каждым тестовым методом
    @Before
    public void setUp() {
        // инициализация объектов
        courierClient = new CourierClient(); // создание экземпляра класса CourierClient
        courier = Courier.getRandom(); // получение случайного объекта курьера
    }

    // тестовый метод для создания курьера с повторяющимся логином
    @Test
    @Description("Создание курьера c помощью рандомного генератора, с целью проверки создания курьера с повторяющим логином")
    @DisplayName("Создание курьера с повторяющимся логином")
    public void createCourierWithDuplicateCredentials() {
        // создание курьера
        courierClient.create(courier);
        // попытка создания курьера с повторяющимся логином
        var createResponse = courierClient.create(courier);
        // получение статус-кода из ответа
        int statusCode = createResponse.extract().statusCode();
        // проверка ожидаемого и фактического сообщений об ошибке
        String expectedMessage = "Этот логин уже используется. Попробуйте другой.";
        String actualMessage = createResponse.extract().path("message");
        Assert.assertEquals(expectedMessage, actualMessage);
        // проверка ожидаемого и фактического статус-кода
        Assert.assertEquals(SC_CONFLICT, statusCode);
    }

    @Test
    @Description("Создание курьера с нулевым значением в поле ввода login")
    @DisplayName("Запрос на создание курьера без логина")
    public void CreateCourierWithEmptyLogin() {
        // устанавливаем значение поля login в null
        courier.setLogin(null);

        // выполняем запрос на создание курьера с использованием courierClient
        var createResponse = courierClient.create(courier);

        // извлекаем статус-код ответа
        statusCode = createResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Недостаточно данных для создания учетной записи";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = createResponse.extract().path("message");

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);

        // проверяем, что статус-код соответствует SC_BAD_REQUEST (коду ошибки "Bad Request")
        Assert.assertEquals(SC_BAD_REQUEST, statusCode);
    }


    @Test
    @Description("Создание курьера с нулевым значением в поле ввода password")
    @DisplayName("Запрос на создание курьера без пароля")
    public void CreateCourierWithEmptyPassword() {
        // устанавливаем значение поля password в null
        courier.setPassword(null);

        // выполняем запрос на создание курьера с использованием courierClient
        var createResponse = courierClient.create(courier);

        // извлекаем статус-код ответа
        statusCode = createResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Недостаточно данных для создания учетной записи";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = createResponse.extract().path("message");

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);

        // проверяем, что статус-код соответствует SC_BAD_REQUEST (коду ошибки "Bad Request")
        Assert.assertEquals(SC_BAD_REQUEST, statusCode);
    }


    @Test
    @Description("Создание курьера с нулевым значением в поле ввода login и password")
    @DisplayName("Запрос на создание курьера без логина и пароля")
    public void CreateCourierWithEmptyCredentials() {
        // устанавливаем значения полей login и password в null
        courier.setLogin(null);
        courier.setPassword(null);

        // выполняем запрос на создание курьера с использованием courierClient
        var createResponse = courierClient.create(courier);

        // извлекаем статус-код ответа
        statusCode = createResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Недостаточно данных для создания учетной записи";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = createResponse.extract().path("message");

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);

        // проверяем, что статус-код соответствует SC_BAD_REQUEST (коду ошибки "Bad Request")
        Assert.assertEquals(SC_BAD_REQUEST, statusCode);
    }


    @Test
    @Description("Вход в систему с пустым значением в поле ввода login")
    @DisplayName("Запрос без логина")
    public void courierLoginWithEmptyLogin() {
        // выполняем запрос на вход в систему без указания логина
        ValidatableResponse LoginResponse = courierClient.loginIsNotValid(new CourierCredentials("", courier.getPassword()));

        // извлекаем статус-код ответа
        statusCode = LoginResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Недостаточно данных для входа";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = LoginResponse.extract().path("message");

        // проверяем, что статус-код соответствует SC_BAD_REQUEST (коду ошибки "Bad Request")
        Assert.assertEquals(SC_BAD_REQUEST, statusCode);

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @Description("Вход в систему с пустым значением в поле ввода password")
    @DisplayName("Запрос без пароля")
    public void courierLoginWithEmptyPassword() {
        // выполняем запрос на вход в систему с пустым значением в поле password
        ValidatableResponse LoginResponse = courierClient.loginIsNotValid(new CourierCredentials(courier.getLogin(), ""));

        // извлекаем статус-код ответа
        statusCode = LoginResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Недостаточно данных для входа";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = LoginResponse.extract().path("message");

        // проверяем, что статус-код соответствует SC_BAD_REQUEST (коду ошибки "Bad Request")
        Assert.assertEquals(SC_BAD_REQUEST, statusCode);

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @Description("Вход в систему с пустым значением в поле ввода login и password")
    @DisplayName("Запрос без логина и пароля")
    public void courierLoginWithEmptyLoginPassword() {
        // выполняем запрос на вход в систему с пустыми значениями в полях login и password
        ValidatableResponse LoginResponse = courierClient.loginIsNotValid(new CourierCredentials("", ""));

        // извлекаем статус-код ответа
        statusCode = LoginResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Недостаточно данных для входа";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = LoginResponse.extract().path("message");

        // проверяем, что статус-код соответствует SC_BAD_REQUEST (коду ошибки "Bad Request")
        Assert.assertEquals(SC_BAD_REQUEST, statusCode);

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @Description("Вход в систему с несуществующим значением в поле ввода login")
    @DisplayName("Запрос с несуществующим логином")
    public void CourierLoginWithNonExistentLogin() {
        // выполняем запрос на вход в систему с несуществующим значением в поле login
        ValidatableResponse loginResponse = courierClient.loginIsNotValid(new CourierCredentials(courier.getLogin().concat("vfhujifvfhujif"), courier.getPassword()));

        // извлекаем статус-код ответа
        statusCode = loginResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Учетная запись не найдена";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = loginResponse.extract().path("message");

        // проверяем, что статус-код соответствует SC_NOT_FOUND (коду ошибки "Not Found")
        Assert.assertEquals(SC_NOT_FOUND, statusCode);

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @Description("Вход в систему с несуществующим значением в поле ввода password")
    @DisplayName("Запрос с несуществующим паролем")
    public void CourierLoginWithNonExistentPassword() {
        // выполняем запрос на вход в систему с несуществующим значением в поле password
        ValidatableResponse loginResponse = courierClient.loginIsNotValid(new CourierCredentials(courier.getLogin(), courier.getPassword().concat("vfhujif2022")));

        // извлекаем статус-код ответа
        statusCode = loginResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Учетная запись не найдена";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = loginResponse.extract().path("message");

        // проверяем, что статус-код соответствует SC_NOT_FOUND (коду ошибки "Not Found")
        Assert.assertEquals(SC_NOT_FOUND, statusCode);

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @Description("Вход в систему с несуществующим значением в поле ввода login и password")
    @DisplayName("Запрос с несуществующей парой логином-пароль")
    public void CourierLoginWithNonExistentLoginPassword() {
        // выполняем запрос на вход в систему с несуществующим значением в полях login и password
        ValidatableResponse loginResponse = courierClient.loginIsNotValid(new CourierCredentials(courier.getLogin().concat("vfhbghjbvujif"), courier.getPassword().concat("vfhujif202213")));

        // извлекаем статус-код ответа
        statusCode = loginResponse.extract().statusCode();

        // ожидаемое сообщение об ошибке
        String expectedMessage = "Учетная запись не найдена";

        // фактическое сообщение об ошибке извлекается из ответа
        String actualMessage = loginResponse.extract().path("message");

        // проверяем, что статус-код соответствует SC_NOT_FOUND (коду ошибки "Not Found")
        Assert.assertEquals(SC_NOT_FOUND, statusCode);

        // проверяем, что ожидаемое сообщение соответствует фактическому сообщению
        Assert.assertEquals(expectedMessage, actualMessage);
    }


}