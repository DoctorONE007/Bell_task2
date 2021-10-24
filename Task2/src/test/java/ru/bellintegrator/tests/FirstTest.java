package ru.bellintegrator.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.bellintegrator.basetestsclass.BaseTests;
import ru.bellintegrator.utils.Listener;

@ExtendWith(Listener.class)
public class FirstTest extends BaseTests {
    @Test
    public void laptopsTest() {
        app.getYandexPage()
                .selectMarket("Маркет")
                .selectBaseMenu("Электроника")
                .selectSubMenu("Ноутбуки")
                .priceInput("10000", "30000")
                .chooseModel("Поставить", "HP")
                .chooseModel("Поставить", "Lenovo")
                .numberOnPage("12")
                .findFirst();

    }

    @Test
    public void phoneTest() {
        app.getYandexPage()
                .selectMarket("Маркет")
                .selectBaseMenu("Электроника")
                .selectSubMenu("Смартфоны")
                .chooseModel("Поставить", "Apple")
                .numberOnPage("12")
                .checkName("iPhone");

    }
}
