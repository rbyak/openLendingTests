package pageObjects;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import components.loaderComponents;
import org.openqa.selenium.WebDriver;
import utils.PageUtil;
import utils.Waits;
import utils.WaitsUtil;


/**
 * This class contains methods that can be used in page objects. All page objects should
 * extend this class.
 *
 */
public abstract class AbstractPage<T extends loaderComponents<T>> extends loaderComponents<T> {

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);

    private final WebDriver webDriver;
    private final WaitsUtil waitsUtil;
    private final Waits waits;
    private final PageUtil pageUtil;

    public AbstractPage(WebDriver webDriver) {
        super();
        this.webDriver = webDriver;
        this.waits = new Waits(this.webDriver);
        this.waitsUtil = new WaitsUtil(this.webDriver);
        this.pageUtil = new PageUtil(this.webDriver, this.waits);
    }

    // Getters
    public PageUtil thePage() {
        return pageUtil;
    }

    public WaitsUtil waitFor() {
        return waitsUtil;
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    protected Waits getWaits() {
        return waits;
    }

    public boolean hasLoaded() {
        var currentUrl = webDriver.getCurrentUrl();
        if (currentUrl == null || currentUrl.equals("data:,") || currentUrl.equals("about:blank")) {
            LOG.info("blank url encountered!");
            return false;
        }
        return true;
    }

    protected void load() {
        LOG.info("Loading [" + this.getClass().getSimpleName() + "].");
    }

    protected boolean isLoaded() throws Exception {
        LOG.info("Checking that [" + this.getClass().getSimpleName() + "] is loaded.");
        return true;
    }

    /**
     * This returns a very simple yes/no answer: is the page loaded. If it's not, then the url will be
     * blank, otherwise, it'll be loaded.
     *
     * @return boolean value for yes/ no for is AlreadyLoaded?
     */
    public boolean isAlreadyLoaded() {
        var currentUrl = webDriver.getCurrentUrl();
        if (currentUrl == null || currentUrl.equals("data:,") || currentUrl.equals("about:blank")) {
            LOG.info("blank url encountered!");
            return false;
        }
        return true;
    }
}


