package pages.phonetest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PAfterSearch {
    protected WebDriver chromeDriver;
    protected WebDriverWait wait;
    private final List<WebElement> result = new ArrayList<>();
    private final WebElement nextPageButton;
    private String massege = "";

    public PAfterSearch(WebDriver chromeDriver, WebDriverWait wait) throws InterruptedException {
        this.chromeDriver = chromeDriver;
        this.wait = wait;
        nextPageButton = chromeDriver.findElement(By.xpath("//a[@aria-label='Следующая страница']"));
    }

    public String getMessege() {
        return massege;
    }

    public boolean findAll(String name) throws InterruptedException {

        do {
            List<WebElement> pageRes = chromeDriver.findElements(By.xpath("//div[@data-apiary-widget-name='@MarketNode/SearchResults']//h3"));
            if (!pageRes.stream().allMatch(x -> x.getText().contains(name))) {
                massege = "Название не совпало на странице " + chromeDriver.findElement(By.xpath("//a[contains(@aria-label, 'текущая')]")).getText();
                return false;
            }
            result.addAll(pageRes);
            nextPageButton.click();
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-apiary-widget-name='@MarketNode/SearchResults']//h3)[1]/a")));

        } while (elementExist(By.xpath("//a[@aria-label='Следующая страница']")));

        return true;
    }

    private boolean elementExist(By by) {
        try {
            chromeDriver.findElement(by);
        } catch (NoSuchElementException ignore) {
            return false;
        }
        return true;

    }

}
