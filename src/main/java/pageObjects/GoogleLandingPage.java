package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleLandingPage extends AbstractPage <GoogleLandingPage>{

    private static final String TITLE = "Google";
    private static final String HOMEPAGE_URL = "https://www.google.com/";

    @FindBy(id = "APjFqb")
    WebElement searchBar;

    @FindBy(css = "div[class='FPdoLc lJ9FBc'] input[name='btnK']")
    WebElement googleSearchButton;


    public GoogleLandingPage(WebDriver webDriver) {
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
            loaded = waitFor().titleToBe(TITLE);
        }
        return loaded
                && waitFor().isVisible(searchBar, googleSearchButton);
    }

    public GoogleSearchResultsPage clickSearch() throws Exception{
        waitFor().visibility(googleSearchButton).click();
        return new GoogleSearchResultsPage(getWebDriver()).get();
    }

    public void addValueToSearchBar(String valueToSearch){
        waitFor().visibility(searchBar).sendKeys(valueToSearch);
    }
}
