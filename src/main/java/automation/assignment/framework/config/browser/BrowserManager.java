package automation.assignment.framework.config.browser;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.edge.EdgeOptions;

public class BrowserManager {
  public void openUrl(String url) throws Exception {
    setupBrowserConfigurations();
    open(url);

    // logBrowserDetails();
    // maximiseWindowAndCloseExtraWindows();
  }

  private void setupBrowserConfigurations() {
    Configuration.browser = "edge";
    Configuration.browserCapabilities =
        new EdgeOptions().addArguments("--remote-allow-origins=*", "InPrivate").addArguments("start-maximized");
    Configuration.headless = false;
    Configuration.pageLoadTimeout = 90000;
    Configuration.browserSize = null;
  }
}
