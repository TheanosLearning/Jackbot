package pageobjects;

import exceptions.UnscrapableDuplicateException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PageUtils;
import utils.WaitUtils;

import java.util.HashMap;
import java.util.List;

public class MyCards extends PageObject {

    protected static final Logger LOG = LoggerFactory.getLogger(MyCards.class);

    private static HashMap<Integer, String> cardTypes = new HashMap<Integer, String>() {{
        put(1, "Bounties"); put(2, "Skills"); put(3, "Characters"); put(4, "Emblems"); put(5, "Weapons");
    }};

    @FindBy(how = How.ID, id = "filterOptions")
    private WebElement filterOptions;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"card-list-view\"]/div/div/div[1]/div[2]/div/div[2]/div/ul/li[7]")
    private WebElement showDuplicates;

    @FindAll({@FindBy(how = How.XPATH, xpath = "//*[@id=\"card-list-view\"]/div/div/div[2]/div/div[2]/div/div/div/div/p/span")})
    private List<WebElement> noCards;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"card-view\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[3]/button")
    private WebElement destroyDuplicates;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"card-view\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[2]/button")
    private WebElement destroySingle;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button")
    private WebElement confirm;

    @FindBy(how = How.XPATH, xpath = "//*[@id=\"card-view\"]/div[1]/div[2]/div/div[1]/div/h4")
    private WebElement cardTitle;

    // programmatic variables
    private boolean doneScrapping = false;
    private int currentGroup = 1;
    private int groupDuplicates = 0;
    private int startIndex = 1;
    private int nextCardIndex = startIndex + groupDuplicates;

    public void selectCardType(int cardType) {
        // refresh page to clear filters
        driver.navigate().refresh();
        WebElement cardTypeButton = WaitUtils.waitUntilElementIsVisible(driver,
                By.xpath("//*[@id=\"card-list-view\"]/div/div/div[1]/div[2]/div/div[1]/button[" + cardType + "]"));
        cardTypeButton.click();
    }

    public void showDuplicates() {
        filterOptions.click();
        showDuplicates.click();
    }

    /**
     * Make sure the cards are loaded by contracting and expanding the card group accordion.
     * The default grouping is by rarity {common: 1, rare: 2, epic: 3, legendary: 4}
     * However, if there are no commons everything shifts (i.e. rare:1 epic:2 etc..)
     */
    public void reloadGrouping(int group, int cardType) {
        String groupXpath = "//*[@id=\"card-list-view\"]/div/div/div[2]/div/div[2]/div/div/div[" + group + "]/div[1]/div[2]/div/div/h4/button";
        try {
            WebElement arrow = driver.findElement(By.xpath(groupXpath));
            arrow.click();
            arrow.click();
        } catch (NoSuchElementException exception) {
            LOG.info("Unable to reload " + cardTypes.get(cardType) + " group " + group);
        }
    }

    public void destroyCardDuplicates(int group, int cardIndex) throws UnscrapableDuplicateException {
        String cardXpath = "//*[@id=\"card-list-view\"]/div/div/div[2]/div/div[2]/div/div/div[" + group + "]/div[2]/div/div[" + cardIndex + "]/div/a";
        try {
            // click on `cardIndex` card
            driver.findElement(By.xpath(cardXpath)).click();
            WaitUtils.waitUntilElementIsClickable(driver, destroyDuplicates);
            destroyDuplicates.click();
            WaitUtils.hardWait(50);
            confirm.click();
            WaitUtils.waitUntilElementIsClickable(driver, destroySingle);
        } catch (TimeoutException ex) {
            // assume the TimeoutException is caused by a duplicate card that can't be dusted
            LOG.info("Unable to scrap card: " + cardTitle.getAttribute("innerHTML"));
            throw new UnscrapableDuplicateException("Unable to scrap duplicate card.");
        } catch(NoSuchElementException exception) {
            LOG.info("Could not find card " + cardIndex + " in group " + group);
        } finally {
            driver.navigate().back();
            WaitUtils.hardWait(2500);
        }
    }

    public void scrapAllDuplicateCardsOfType(int cardType) {
        selectCardType(cardType);
        showDuplicates();
        if(noCards.size() > 0) {
            LOG.info("No duplicate " + cardTypes.get(cardType) + " found.");
            return;
        }
        while(!doneScrapping) {
            reloadGrouping(currentGroup, cardType);
            scrapCardsGroup(currentGroup, cardType);
            // renew the page after completing a group so the group index will update
            selectCardType(cardType);
            showDuplicates();
        }
        // all duplicate cards of cardType scrapped
    }

    public void scrapCardsGroup(int group, int cardType) {
        String cardsGroupXpath = "//*[@id=\"card-list-view\"]/div/div/div[2]/div/div[2]/div/div/div[" + group + "]/div[2]/div";
        try {
            while(PageUtils.getNumberOfChildren(driver, cardsGroupXpath) >= nextCardIndex) {
                scrapNextCard(group);
            }
            if(groupDuplicates > 0) {
                currentGroup++;
            }
            groupDuplicates = 0;
            nextCardIndex = startIndex + groupDuplicates;
        } catch (NoSuchElementException exception) {
            // we should only get these exceptions when trying to access a group out of bounds
            LOG.info("No more " + cardTypes.get(cardType) + " to scrap.");
            doneScrapping = true;
        }
    }

    public void scrapNextCard(int group) {
        try {
            destroyCardDuplicates(group, nextCardIndex);
            nextCardIndex = groupDuplicates + startIndex + 1;
        } catch (UnscrapableDuplicateException exception) {
            groupDuplicates++;
            nextCardIndex = groupDuplicates + startIndex;
        }
    }

    public MyCards(WebDriver driver) {
        super(driver);
    }

}
