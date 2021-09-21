package pages.phonetest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class PElectronicsSearch {
    protected WebDriver chromeDriver;

    private final WebElement electronicsButton;

    public PElectronicsSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        closeAdv(By.xpath("//div[@data-apiary-widget-name='@MarketNode/DailyBonusesPopup']//*[local-name() = 'path']"), null);
        electronicsButton = chromeDriver.findElement(By.xpath("//div[@data-zone-name= 'menu']//*[contains(text(),'Электроника')]"));
    }

    public void find() {
        electronicsButton.click();
    }

    public void closeAdv(By by, By frameXPath) {
        chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            if (frameXPath != null)
                chromeDriver.switchTo().frame(chromeDriver.findElement(frameXPath));
            chromeDriver.findElement(by);
            chromeDriver.findElement(by).click();
        } catch (NoSuchElementException ignore) {
        } finally {
            chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (frameXPath != null)
                chromeDriver.switchTo().parentFrame();
        }

    }
}
