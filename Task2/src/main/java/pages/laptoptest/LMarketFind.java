package pages.laptoptest;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class LMarketFind {
    protected WebDriver chromeDriver;

    private final WebElement marketButton;

    public LMarketFind(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        marketButton = chromeDriver.findElement(By.xpath("//li[@class = 'services-new__list-item']//*[contains(text(),'Маркет')]"));
    }

    public void find() {
        marketButton.click();
    }

    public void switchToTabByText(String text) {
        String myTab = chromeDriver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<>(chromeDriver.getWindowHandles());
        for (String s : newTab) {
            if (!s.equals(myTab)) {
                chromeDriver.switchTo().window(s);
                if (chromeDriver.getTitle().contains(text))
                    return;
            }
        }
        Assertions.fail("Вкладка " + text + " не найдена");
    }
}
