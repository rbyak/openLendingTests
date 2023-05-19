package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
public class InsightsPage extends AbstractPage<InsightsPage> {

    private static final String TITLE = "Insights | Open Lending | United States";
    private static final String HOMEPAGE_URL = "https://www.openlending.com/category/blog/";

    @FindBy(id = "featured-box-section")
    WebElement contentBox;

    public InsightsPage(WebDriver webDriver) {
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
                && waitFor().isVisible(contentBox);
    }

    public List<WebElement> getBlogEntries(){
        List<WebElement> blogEntries = getWebDriver().findElements(By.xpath("//h3[@class='entry-title']/a"));
        return blogEntries;
    }

    public boolean checkIfThereIsNextPage(){
        WebElement nextPageLink = getWebDriver().findElement(By.className("next"));
        if (nextPageLink.getAttribute("class").contains("disabled")) {
            // No more pages, break the loop
            return false;
        }

        return true;
    }
}
