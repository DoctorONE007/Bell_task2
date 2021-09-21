package pages.phonetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PModelSearch {
    protected WebDriver chromeDriver;
    protected WebDriverWait wait;
    private final WebElement appleCheckbox;
    private final WebElement numberOfElement;
    private final WebElement numberOfElement12;

    public PModelSearch(WebDriver chromeDriver, WebDriverWait wait, String numberOnPage, String model) {
        this.chromeDriver = chromeDriver;
        this.wait = wait;
        appleCheckbox = chromeDriver.findElement(By.xpath("//div[@class='_3_phr']//*[contains(text(),'" + model + "')]"));
        numberOfElement = chromeDriver.findElement(By.xpath("//span[contains(text(),'Показывать по')]/../.."));
        numberOfElement12 = chromeDriver.findElement(By.xpath("//button[contains(text(),'Показывать по " + numberOnPage + "')]"));

    }


    public void find() throws InterruptedException {
        appleCheckbox.click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-apiary-widget-name='@MarketNode/SearchResults']//h3)[1]/a")));
        numberOfElement.click();
        numberOfElement12.click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-apiary-widget-name='@MarketNode/SearchResults']//h3)[1]/a")));

    }
}
