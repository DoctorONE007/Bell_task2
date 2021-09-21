package pages.phonetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PPhoneSearch {
    protected WebDriver chromeDriver;

    private final WebElement phoneButton;

    public PPhoneSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        phoneButton = chromeDriver.findElement(By.xpath("//div[@data-apiary-widget-name='@MarketNode/NavigationTree']//*[text()='Смартфоны']"));
    }

    public void find() {
        phoneButton.click();
    }

}

