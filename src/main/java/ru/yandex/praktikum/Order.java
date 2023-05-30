package ru.yandex.praktikum;

import java.util.List;

public class Order {
    private final String firstName = "Инна"; // фиксированное значение имени
    private final String lastName = "Малышева"; // фиксированное значение фамилии
    private final String address = "Комсомольский проспект, 10"; // фиксированный адрес
    private final String metroStation = "Сокольники"; // фиксированное значение станции метро
    private final String phone = "+795296008947"; // фиксированный номер телефона
    private final String rentTime = "2"; // фиксированное время аренды
    private final String deliveryDate = "19-05-2023"; // фиксированная дата доставки
    private final String comment = "Тестирование"; // фиксированный комментарий
    private List<String> color; // список цветов

    public Order() {
    }

    public Order(List<String> color) {
        this.color = color; // устанавливается переданный список цветов
    }

    public List<String> getColor() {
        return color; // возвращается список цветов
    }

    public void setColor(List<String> color) {
        this.color = color; // устанавливается новый список цветов
    }
}