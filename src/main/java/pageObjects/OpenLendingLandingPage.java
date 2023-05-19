package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenLendingLandingPage extends AbstractPage<OpenLendingLandingPage> {

    private static final String TITLE = "Automated Lending Platform | Open Lending | United States";
    private static final String HOMEPAGE_URL = "https://www.openlending.com/";

    @FindBy(css = "li[id='menu-item-521'] a:nth-child(2)")
    WebElement resourcesItem;

    public OpenLendingLandingPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Override
    protected void load() {
        super.load();
        getWebDriver().navigate().to(HOMEPAGE_URL);
    }

    @Override
    protected boolean isLoaded() throws Exception {
        super.isLoaded();
        boolean loaded;
        loaded = hasLoaded();
        if (getWebDriver().getCurrentUrl().equals(HOMEPAGE_URL)) {
            loaded = waitFor().titleToContain(TITLE);
        }
        return loaded
                && waitFor().isVisible(resourcesItem);
    }

    public InsightsPage hoverOverResourcesMenuAndClickInsights() throws Exception {
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(resourcesItem).perform();
        waitFor().visibility(By.className("sub-menu"));
        WebElement insightMenuItem =  getWebDriver().findElement(By.linkText("Insights"));
        insightMenuItem.click();
        return new InsightsPage(getWebDriver()).get();
    }
}
