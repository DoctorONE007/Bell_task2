package pages.laptoptest;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LPriceAndModelSearch {
    protected WebDriver chromeDriver;
    protected WebDriverWait wait;
    private final WebElement minPriceField;
    private final WebElement maxPriceField;
    private final WebElement hpCheckbox;
    private final WebElement lenovoCheckbox;
    private final WebElement numberOfElement;
    private final WebElement numberOfElement12;

    public LPriceAndModelSearch(WebDriver chromeDriver, WebDriverWait wait, String numberOnPage) {
        this.chromeDriver = chromeDriver;
        this.wait = wait;
        minPriceField = chromeDriver.findElement(By.xpath("//input[@name='Цена от']"));
        maxPriceField = chromeDriver.findElement(By.xpath("//input[@name='Цена до']"));
        hpCheckbox = chromeDriver.findElement(By.xpath("//div[@class='_3_phr']//*[contains(text(),'HP')]"));
        lenovoCheckbox = chromeDriver.findElement(By.xpath("//div[@class='_3_phr']//*[contains(text(),'Lenovo')]"));
        numberOfElement = chromeDriver.findElement(By.xpath("//span[contains(text(),'Показывать по')]/../.."));
        numberOfElement12 = chromeDriver.findElement(By.xpath("//button[contains(text(),'Показывать по " + numberOnPage + "')]"));

    }

    public void input(String minprice, String maxprice) {
        minPriceField.click();
        minPriceField.sendKeys(minprice);
        maxPriceField.click();
        maxPriceField.sendKeys(maxprice);

    }

    public void find() throws InterruptedException {
        hpCheckbox.click();
        lenovoCheckbox.click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-apiary-widget-name='@MarketNode/SearchResults']//h3)[1]/a")));
        numberOfElement.click();
        numberOfElement12.click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-apiary-widget-name='@MarketNode/SearchResults']//h3)[1]/a")));

    }


}
