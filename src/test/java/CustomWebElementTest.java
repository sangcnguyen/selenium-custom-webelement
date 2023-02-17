import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomWebElementTest {

  WebDriver driver;

  @BeforeClass
  static void setupAll() {
    WebDriverManager.chromedriver().setup();
  }

  @AfterTest
  void setup() {
    driver = new ChromeDriver();
  }

  @AfterTest
  void teardown() {
    driver.quit();
  }

  @Test
  void test() {
    // Your test logic here
  }
}
