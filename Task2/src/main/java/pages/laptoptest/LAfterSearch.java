package pages.laptoptest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LAfterSearch {
    protected WebDriver chromeDriver;

    public LAfterSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public List<WebElement> getResults() {
        return chromeDriver.findElements(By.xpath("//div[@data-apiary-widget-name='@MarketNode/SearchResults']//h3"));
    }
}
