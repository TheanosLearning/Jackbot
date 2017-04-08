package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GearsHome extends PageObject {

    public static final String BASE_URL = "https://gearsofwar.com";

    @FindBy(how = How.CSS, css = "span.signin-label")
    private WebElement signInButton;

    @FindBy(how = How.XPATH, xpath = "//nav[@id='menu']/ul/li[5]/a/span")
    private WebElement cardsNav;

    public void clickSignInButton() {
        signInButton.click();
    }

    /**
     * Top nav contains: Games, Community, Forums, Esports, Cards, Store
     */
    public void clickCardsNav() {
        cardsNav.click();
    }

    public GearsHome(WebDriver driver) {
        super(driver, BASE_URL);
    }

}
