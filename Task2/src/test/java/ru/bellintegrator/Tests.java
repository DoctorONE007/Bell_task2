package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.laptoptest.*;
import pages.phonetest.*;

public class Tests extends BaseTest {

    @Feature("Проверка поиска ноутбуков")
    @Test
    public void laptopPO() throws InterruptedException {

        chromeDriver.get("https://yandex.ru/");
        int numberOnPage = 12;

        //Перейти в Яндекс Маркет
        LMarketFind lMarketFind = new LMarketFind(chromeDriver);
        lMarketFind.find();
        lMarketFind.switchToTabByText("Яндекс.Маркет — покупки с быстрой доставкой");

        //Выбрать раздел компьютеры
        LComputersSearch lComputersSearch = new LComputersSearch(chromeDriver);
        lComputersSearch.find();

        //Выбрать раздел Ноутбуки
        LLaptopSearch lLaptopSearch = new LLaptopSearch(chromeDriver);
        lLaptopSearch.find();

        //Выбрать параметры цены, производителя и количество элементов на странице
        LPriceAndModelSearch lPriceAndModelSearch = new LPriceAndModelSearch(chromeDriver, wait, String.valueOf(numberOnPage));
        lPriceAndModelSearch.input("10000", "30000");
        lPriceAndModelSearch.find();

        //Проверка количества элементов на странице
        LAfterSearch lAfterSearch = new LAfterSearch(chromeDriver);
        Assertions.assertEquals(lAfterSearch.getResults().size(), numberOnPage,
                "Количество элементов на странице не совпадает");

        //Обрезаем модель 1 Ноутбука
        String firstSearch = lAfterSearch.getResults().get(0).getText().split("\\(")[0];

        //Поиск по модели
        LSearch lSearch = new LSearch(chromeDriver);
        lSearch.find(firstSearch);

        //Проверка что в выведенных моделях есть сохраненная
        LAfterSearch lAfterSearchNew = new LAfterSearch(chromeDriver);
        Assertions.assertTrue(lAfterSearchNew.getResults().stream().anyMatch(x -> x.getText().contains(firstSearch)),
                "Ноутбук с именем " + firstSearch + " не найден");
    }
    @Feature("Проверка поиска смартфонов")
    @Test
    public void phonePO() throws InterruptedException {
        chromeDriver.get("https://yandex.ru/");
        int numberOnPage = 12;
        String model = "Apple";
        String phoneName = "iPhone";

        //Перейти в Яндекс Маркет
        PMarketFind pMarketFind = new PMarketFind(chromeDriver);
        pMarketFind.find();
        pMarketFind.switchToTabByText("Яндекс.Маркет — покупки с быстрой доставкой");

        //Выбрать раздел Электроника
        PElectronicsSearch pElectronicsSearch = new PElectronicsSearch(chromeDriver);
        pElectronicsSearch.find();

        //Выбрать раздел Смартфоны
        PPhoneSearch pPhoneSearch = new PPhoneSearch(chromeDriver);
        pPhoneSearch.find();

        //Выбрать параметр производителя и количество элементов на странице
        PModelSearch pModelSearch = new PModelSearch(chromeDriver, wait, String.valueOf(numberOnPage), model);
        pModelSearch.find();

        //Проверка, что на каждой странице совпадает название
        PAfterSearch pAfterSearch = new PAfterSearch(chromeDriver, wait);
        Assertions.assertTrue(pAfterSearch.findAll(phoneName), pAfterSearch.getMessege());
    }
}
