import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HoverTest {

  WebDriver webDriver;

  @BeforeClass
  static void setupAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeTest
  void setup() {
    webDriver = new ChromeDriver();
  }

  @AfterTest
  void teardown() {
    webDriver.quit();
  }

  @Test
  void test() {
    HoverPage hoverPage = new HoverPage(webDriver);
    hoverPage.goToPage();
    String username = hoverPage.getHoverContent();
  }
}
