package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageUtils {

    // gets the number of immediate children of an element from an xpath
    public static int getNumberOfChildren(WebDriver driver, String xPath) {
        return driver.findElement(By.xpath(xPath)).findElements(By.xpath("*")).size();
    }

}
