package pageobjects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageObject {

    protected WebDriver driver;

    public PageObject() {}

    public PageObject(WebDriver driver) {
        this(driver, null);
    }

    public PageObject(WebDriver driver, String url) {
        this.driver = driver;

        if (!StringUtils.isEmpty(url)) {
            this.driver.get(url);
        }

        this.load();
    }

    public void load() {
        PageFactory.initElements(this.driver, this);
    }

}
