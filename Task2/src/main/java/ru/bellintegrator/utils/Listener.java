package ru.bellintegrator.utils;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.bellintegrator.managers.DriverManager;

import java.io.ByteArrayInputStream;

public class Listener implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        byte[] byteImage = ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(byteImage), null);
    }
}
