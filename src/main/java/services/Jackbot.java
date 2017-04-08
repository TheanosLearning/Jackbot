package services;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.Cards;
import pageobjects.GearsHome;
import pageobjects.MyCards;
import pageobjects.OutlookLogin;
import utils.ArgUtils;

/**
 * Jackbot scraps all duplicate characters, emblems & weapon skins by default
 * Tested with:
 * chromedriver v2.29
 * chrome v57.0 (64-bit)
 */
public class Jackbot {

    protected static final Logger LOG = LoggerFactory.getLogger(Jackbot.class);
    protected static WebDriver driver;

    public static void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void tearDown() {
        driver.quit();
    }

    public static void main(String[] args) {
        setUp();
        goToMyCards(args);
        //scrapDuplicates(3);// Characters
        //scrapDuplicates(4);// Emblems
        scrapDuplicates(5);// Weapons
        tearDown();
    }

    public static void goToMyCards(String[] args) {
        GearsHome gearsHome = new GearsHome(driver);
        gearsHome.clickSignInButton();
        OutlookLogin outlook = new OutlookLogin(driver);
        if(ArgUtils.isValid(args)) {
            outlook.signIn(args[0], args[1]);
        }
        gearsHome.clickCardsNav();
        Cards cards = new Cards(driver);
        cards.clickMyCards();
    }

    public static void scrapDuplicates(int cardType) {
        MyCards myCards = new MyCards(driver);
        myCards.scrapAllDuplicateCardsOfType(cardType);
    }

}
