package ru.bellintegrator.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class YandexPage extends BasePage {
    @FindBy(xpath = "//li[@class = 'services-new__list-item']//*[contains(text(),'Маркет')]")
    private List<WebElement> listYandexMenu;

    @Step("Выбрать {name}")
    public MarketPage selectMarket(String name) {
        for (WebElement element : listYandexMenu) {
            if (element.getText().equals(name)) {
                waitUtilElementToBeClickable(element);
                element.click();
                switchToTabByText("Яндекс.Маркет — покупки с быстрой доставкой");
                return pageManager.getHomePage();

            }
        }
        Assertions.fail("Кнопка " + name + "  не найдена");
        return null;
    }

    /**
     * Переключение вкладок
     * @param text текст вкладки, на котоую переключить
     */
    public void switchToTabByText(String text) {
        String myTab = driverManager.getDriver().getWindowHandle();
        ArrayList<String> newTab = new ArrayList<>(driverManager.getDriver().getWindowHandles());
        for (String s : newTab) {
            if (!s.equals(myTab)) {
                driverManager.getDriver().switchTo().window(s);
                if (driverManager.getDriver().getTitle().contains(text))
                    return;
            }
        }
        Assertions.fail("Вкладка " + text + " не найдена");
    }
}
