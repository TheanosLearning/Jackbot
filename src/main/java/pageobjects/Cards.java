package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class Cards extends PageObject {

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"main-menu\"]/ul/li[1]/a")
    private WebElement myCards;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"main-menu\"]/ul/li[2]/a")
    private WebElement myPacks;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"main-menu\"]/ul/li[3]/a")
    private WebElement store;

    public void clickMyCards() {
        myCards.click();
    }

    public Cards(WebDriver driver) {
        super(driver);
    }

}
