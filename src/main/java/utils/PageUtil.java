package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.AbstractPage;

/** This utility class contains basic methods that are useful for determining a page's state. */
public class PageUtil {
  protected static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);


  private final WebDriver webDriver;
  private final Waits waits;

  public PageUtil(WebDriver webDriver, Waits waits) {
    this.webDriver = webDriver;
    this.waits = waits;
  }

  /**
   * Returns whether the current browser path contains a string of the path
   *
   *
   * @param webDriverWait
   * @param path is a string
   * @return false if title contains 404, or if exception is thrown while waiting for browser path
   *     to contain path
   */
  public boolean currentPathContains(WebDriverWait webDriverWait, String path) {

    return currentPathContains(waits.largeWait(), path);
  }

  public boolean currentTitleContains(WebDriverWait webDriverWait, String title) {

    return currentTitleContains(waits.largeWait(), title);
  }

}

