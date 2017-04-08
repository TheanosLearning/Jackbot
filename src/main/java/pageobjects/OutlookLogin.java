package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class OutlookLogin extends PageObject {

    protected static final Logger LOG = LoggerFactory.getLogger(OutlookLogin.class);

    @FindBy(how = How.ID, id = "i0116")
    private WebElement usernameField;

    @FindBy(how = How.ID, id = "i0118")
    private WebElement passwordField;

    @FindBy(how = How.ID, id = "idSIButton9")
    private WebElement nextButton;

    @FindBy(how = How.ID, id = "idSIButton9")
    private WebElement signInButton;

    public void signIn(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        nextButton.click();
        WaitUtils.waitUntilElementIsVisible(driver, passwordField).click();
        passwordField.clear();
        passwordField.sendKeys(password);
        signInButton.click();
    }

    public OutlookLogin(WebDriver driver) {
        super(driver);
    }

}
