package ru.bellintegrator.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Реализация домашней страницы
 */
public class MarketPage extends BasePage {
    @FindBy(xpath = "//div[@data-apiary-widget-name='@MarketNode/DailyBonusesPopup']//*[local-name() = 'path']")
    private WebElement advButton;
    @FindBy(xpath = "//div[@data-zone-name= 'menu']//div[@data-zone-name='category-link']")
    private List<WebElement> listBaseMenu;
    @FindBy(xpath = "//div[@data-apiary-widget-name='@MarketNode/NavigationTree']//ul[@data-autotest-id='subItems']/li")
    private List<WebElement> listSubMenu;
    @FindBy(xpath = "//input[@name='Цена от']")
    private WebElement minPriceInput;
    @FindBy(xpath = "//input[@name='Цена до']")
    private WebElement maxPriceInput;
    @FindBy(xpath = "//div[@class='_3_phr']//legend[contains(text(),'Производитель')]/..//span")
    private List<WebElement> modelsNameList;
    @FindBy(xpath = "//span[contains(text(),'Показывать по')]/../..")
    private WebElement numberOnPageButton;
    @FindBy(xpath = "//button[contains(text(),'Показывать по')]")
    private List<WebElement> numbersList;
    @FindBy(xpath = "//div[@class='_2Lvbi _1oZmP']")
    private WebElement loadElement;
    @FindBy(xpath = "//div[@data-apiary-widget-name='@MarketNode/SearchResults']//h3")
    private List<WebElement> resultsAfterSearch;
    @FindBy(xpath = "//form[@action='/search']//input[@type ='text']")
    private WebElement searchField;
    @FindBy(xpath = "//form[@action='/search']//*[local-name() = 'span'][contains(text(),'Найти')]")
    private WebElement searchButton;
    @FindBy(xpath = "//a[@aria-label='Следующая страница']")
    private WebElement nextPageButton;
    @FindBy(xpath = "//a[contains(@aria-label, 'текущая')]")
    private WebElement currentPage;


    /**
     * Функция клика на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     * @return HomePage - т.е. остаемся на этой странице
     */
    @Step("Открытие меню {nameBaseMenu}")
    public MarketPage selectBaseMenu(String nameBaseMenu) {
        closeAdv(advButton, null);
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().equals(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem);
                menuItem.click();
                return this;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return null;
    }

    /**
     * Функция клика на любое подменю
     *
     * @param nameSubMenu - наименование подменю
     * @return MarketPage
     */
    @Step("Открыть подменю {nameSubMenu}")
    public MarketPage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equals(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem);
                menuItem.click();
                return this;
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return null;
    }

    /**
     * Функция ввода цены
     * @param min - минимальная цена
     * @param max - максимальная цена
     * @return MarketPage
     */
    @Step("Ввести цену от {min} до {max}")
    public MarketPage priceInput(String min, String max) {
        inputPrice(minPriceInput, min);
        inputPrice(maxPriceInput, max);
        return this;
    }

    @Step("Выбрать чекбокс модели {nameField}")
    public MarketPage chooseModel(String operation, String nameField) {
        String booleanFlag = operation.equals("Поставить") ? "true" : "false";
        for (WebElement checkBox : modelsNameList) {
            if (checkBox.getText().equals(nameField)) {
                checkBox.click();
                Assertions.assertEquals(booleanFlag, checkBox.findElement(By.xpath("./../..//input")).getAttribute("checked")
                        , "CheckBox '" + nameField + "' не выполнился");
                return this;
            }
        }

        Assertions.fail("CheckBox с наименованием '" + nameField + "' отсутствует на странице ");
        return null;

    }

    /**
     * Вабор количества элементов на странице
     * @param number - количество элементов
     * @return MarketPage
     */
    @Step("Установить количество показываемых элементов на страницу {number}")
    public MarketPage numberOnPage(String number) {
        if (isLoaded()) {
            waitUtilElementToBeClickable(numberOnPageButton);
            numberOnPageButton.click();
            for (WebElement element : numbersList) {
                if (element.getText().contains(number)) {
                    element.click();
                    if (isLoaded())
                        Assertions.assertEquals(String.valueOf(resultsAfterSearch.size()), number,
                                "Количество элементов на странице не совпадает");
                    return this;
                }
            }
        }
        Assertions.fail("Количество элементов" + number + " нельзя выбрать");
        return null;
    }

    @Step("Проверка первого элемента")
    public MarketPage findFirst() {
        String firstSearch = resultsAfterSearch.get(0).getText().split("\\(")[0];
        input(firstSearch);
        Assertions.assertTrue(resultsAfterSearch.stream().anyMatch(x -> x.getText().contains(firstSearch)),
                "Ноутбук с именем " + firstSearch + " не найден");
        return this;

    }

    @Step("Проверка названий на всех страницах")
    public MarketPage checkName(String name) {
        do {
            if (!resultsAfterSearch.stream().allMatch(x -> x.getText().contains(name)))
                Assertions.fail("Название не совпало на странице " + currentPage.getText());
            nextPageButton.click();
            isLoaded();

        } while (waitUtilElementToBeVisible(nextPageButton));
        return this;
    }

    /**
     * Проверка загружены ли элементы
     * @return boolean
     */
    private boolean isLoaded() {
        waitUtilElementToBeVisible(loadElement);
        if (!waitUtilElementNotToBeVisible(loadElement))
            return true;
        return false;

    }

    /**
     * Ввод в строку поиска
     * @param value - что вводить
     */
    private void input(String value) {
        searchField.click();
        searchField.clear();
        searchField.sendKeys(value);
        wait.until(ExpectedConditions.attributeToBe(searchField, "value", value));
        Assertions.assertEquals(value, searchField.getAttribute("value")
                , "Поле поиска было заполнено некорректно");
        searchButton.click();
    }

    /**
     * Ввод цены
     * @param element - поле
     * @param value - что вводить
     */
    private void inputPrice(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
        Assertions.assertEquals(value, element.getAttribute("value")
                , "Поле цены было заполнено некорректно");
    }

    /**
     * Закрытие рекламы
     * @param element - полу
     * @param frameXPath - путь к фрейму
     */
    private void closeAdv(WebElement element, By frameXPath) {
        driverManager.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            if (frameXPath != null)
                driverManager.getDriver().switchTo().frame(driverManager.getDriver().findElement(frameXPath));
            element.click();
        } catch (NoSuchElementException ignore) {
        } finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (frameXPath != null)
                driverManager.getDriver().switchTo().parentFrame();
        }

    }

}
