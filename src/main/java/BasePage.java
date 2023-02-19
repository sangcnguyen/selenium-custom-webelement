import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

  private static final String BASE_URL = "https://the-internet.herokuapp.com";
  protected String path;
  public WebDriver webDriver;

  public BasePage(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(new CustomFieldDecorator(webDriver), this);
  }

  public void goToPage() {
    this.webDriver.get(BASE_URL.concat(this.path));
    webDriver.manage().timeouts().implicitlyWait( Duration.ofSeconds(10));
  }
}
