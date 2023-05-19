package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleSearchResultsPage extends AbstractPage<GoogleSearchResultsPage> {

  private static final String TITLE = " - Google Search";

  public GoogleSearchResultsPage(WebDriver webDriver) {
    super(webDriver);
    PageFactory.initElements(webDriver, this);
  }

  @Override
  protected boolean isLoaded() throws Exception {
    super.isLoaded();
    return thePage().currentTitleContains(getWaits().largeWait(), TITLE);
  }

  public OpenLendingLandingPage findOpenLendingPage() throws Exception {
    List<WebElement> searchResultLinks =
        getWebDriver().findElements(By.xpath("//h3[@class='LC20lb DKV0Md']"));

    for (WebElement link : searchResultLinks) {
      String linkText = link.getText();
      if (linkText.equals("Automated Lending Platform | Open Lending | United States")) {
        // Click on the desired link
        return new OpenLendingLandingPage(getWebDriver()).go();
      } else throw new Exception("Cannot find result");
    }

    return null;
  }
}
