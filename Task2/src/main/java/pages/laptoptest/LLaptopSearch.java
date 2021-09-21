package pages.laptoptest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LLaptopSearch {
    protected WebDriver chromeDriver;

    private final WebElement laptopButton;

    public LLaptopSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        laptopButton = chromeDriver.findElement(By.xpath("//div[@data-apiary-widget-name='@MarketNode/NavigationTree']//*[text()='Ноутбуки']"));
    }

    public void find() {
        laptopButton.click();
    }

}

