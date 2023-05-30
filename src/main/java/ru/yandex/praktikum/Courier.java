package ru.yandex.praktikum;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    public String login; // поле для хранения логина курьера
    public String password; // поле для хранения пароля курьера
    public String firstName; // поле для хранения имени курьера


    public Courier(String login, String password, String firstName) {
        this.login = login; // инициализация поля login с помощью переданного аргумента
        this.password = password; // инициализация поля password с помощью переданного аргумента
        this.firstName = firstName; // инициализация поля firstName с помощью переданного аргумента
    }

    public Courier() {
    }

    public String getLogin() {
        return login; // возвращает значение поля login
    }

    public String getPassword() {
        return password; // возвращает значение поля password
    }

    public String getFirstName() {
        return firstName; // возвращает значение поля firstName
    }

    public void setLogin(String login) {
        this.login = login; // устанавливает значение поля login
    }

    public void setPassword(String password) {
        this.password = password; // устанавливает значение поля password
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName; // устанавливает значение поля firstName
    }

    // аннотация для отображения в отчёте фреймворка Allure
    @Step("Создание случайного курьера")
    public static Courier getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10); // генерирует случайную строку из 10 алфавитных символов для login
        final String password = RandomStringUtils.randomAlphabetic(10); // генерирует случайную строку из 10 алфавитных символов для password
        final String firstName = RandomStringUtils.randomAlphabetic(10); // генерирует случайную строку из 10 алфавитных символов для firstName

        Allure.addAttachment("Логин: ", login); // добавляет вложение в отчёт Allure с названием "Логин: " и значением login
        Allure.addAttachment("Пароль: ", password); // добавляет вложение в отчёт Allure с названием "Пароль: " и значением password
        Allure.addAttachment("Имя: ", firstName); // добавляет вложение в отчёт Allure с названием "Имя: " и значением firstName

        return new Courier(login, password, firstName); // создаёт новый объект Courier с заданными значениями и возвращает его
    }

    @Override
    public String toString() {
        // формируем строковое представление объекта Courier
        return "Courier{" +
                "login='" + login + '\'' + // Добавляем логин
                ", password='" + password + '\'' + // Добавляем пароль
                ", firstName='" + firstName + '\'' + // Добавляем имя
                '}';
    }

}