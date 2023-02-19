import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HoverPage extends BasePage {

  public HoverPage(WebDriver webDriver) {
    super(webDriver);
    this.path = "/hovers";
  }

  @FindBy(xpath = "//*[@class='figure']/img")
  public List<CustomWebElement> itemMenu;

  @FindBy(css = ".figcaption > h5")
  public List<CustomWebElement> usernameTooltip;

  public String getTextWhenHoveringAvatar() {
    itemMenu.get(0).hover();
    return usernameTooltip.get(0).getText();
  }
}
