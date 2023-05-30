package ru.yandex.praktikum;

public class CourierCredentials {
    public String login; // логин курьера
    public String password; // пароль курьера

    public CourierCredentials(String login, String password) {
        this.login = login; // устанавливается переданный логин
        this.password = password; // устанавливается переданный пароль
    }

    public CourierCredentials() {
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword()); // создается новый объект CourierCredentials на основе данных из объекта Courier
    }

    public String getLogin() {
        return login; // возвращается логин
    }

    public String getPassword() {
        return password; // возвращается пароль
    }

    public void setLogin(String login) {
        this.login = login; // устанавливается новый логин
    }

    public void setPassword(String password) {
        this.password = password; // устанавливается новый пароль
    }

    @Override
    public String toString() {
        return "CourierCredentials{" +
                "login='" + login + '\'' + // добавляется логин
                ", password='" + password + '\'' + // добавляется пароль
                '}';
    }
}