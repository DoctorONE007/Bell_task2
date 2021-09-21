package pages.laptoptest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LSearch {
    protected WebDriver chromeDriver;

    private final WebElement searchField;
    private final WebElement searchButton;

    public LSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        searchField = chromeDriver.findElement(By.xpath("//form[@action='/search']//input[@type ='text']"));
        searchButton = chromeDriver.findElement(By.xpath("//form[@action='/search']//*[local-name() = 'span'][contains(text(),'Найти')]"));
    }

    public void find(String keysFind) {
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }
}
