package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitUtils {

    private static final Sleeper sleeper = Sleeper.SYSTEM_SLEEPER;
    private static final int TIME_OUT = 15;// seconds

    public static WebElement waitUntilElementIsVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement waitUntilElementIsVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilElementIsPresent(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement waitUntilElementIsClickable(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement waitUntilElementIsClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void hardWait(long duration) {
        final Duration _duration = new Duration(duration, TimeUnit.MILLISECONDS);
        try {
            sleeper.sleep(_duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WebDriverException(e);
        }
    }

}
