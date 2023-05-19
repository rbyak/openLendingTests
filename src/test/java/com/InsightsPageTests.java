package com;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.openqa.selenium.WebElement;
import pageObjects.GoogleLandingPage;

import java.util.HashSet;

public class InsightsPageTests extends DesktopTestBase {

  @Test
  public void noDuplicateBlogEntries() throws Exception {
    var googleLandingPage = new GoogleLandingPage(getWebDriver()).get();
    googleLandingPage.addValueToSearchBar("Open Lending");
    var googleSearchResultsPage = googleLandingPage.clickSearch();
    var openLendingPage = googleSearchResultsPage.findOpenLendingPage();
    var insightsPage = openLendingPage.hoverOverResourcesMenuAndClickInsights();

    var pageNumber = 1;
    var uniqueEntries = new HashSet<>();
    while (true) {
      var blogEntries = insightsPage.getBlogEntries();
      uniqueEntries = new HashSet<>();
      for (WebElement entry : blogEntries) {
        // Get the text of the blog entry
        String entryText = entry.getText();
        // Check if the blog entry is already in the set
        assertThat(uniqueEntries.contains(entryText))
            .as("Duplicate Entry Found" + entryText)
            .isFalse();
      }
      if (insightsPage.checkIfThereIsNextPage()) pageNumber++;

      insightsPage
          .getWebDriver()
          .navigate()
          .to("https://www.openlending.com/category/blog/page/" + pageNumber);
    }
  }
}
