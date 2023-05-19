package ru.yandex.praktikum;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class RestAssuredClient {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";

    /**
     * Метод для получения базовой спецификации для RestAssured запросов.
     *
     * @return RequestSpecification, объект, представляющий базовую спецификацию.
     */
    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured()) // добавляется фильтр для интеграции с Allure отчетами
                .log(LogDetail.ALL) // включается логирование всех деталей запроса и ответа
                .setContentType(JSON) // устанавливается тип контента запроса в JSON
                .setBaseUri(BASE_URL) // устанавливается базовый URI для запросов
                .build(); // строится и возвращается объект RequestSpecification
    }
}