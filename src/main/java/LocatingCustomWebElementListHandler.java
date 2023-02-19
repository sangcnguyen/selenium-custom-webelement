import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class LocatingCustomWebElementListHandler implements InvocationHandler {

  private final ElementLocator locator;
  private final WebDriver webDriver;

  public LocatingCustomWebElementListHandler(ElementLocator locator, WebDriver webDriver) {
    this.locator = locator;
    this.webDriver = webDriver;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    List<WebElement> elements = locator.findElements();
    List<CustomWebElementImpl> customWebElements = elements.stream()
        .map(element -> new CustomWebElementImpl(element, webDriver))
        .collect(Collectors.toList());

    try {
      return method.invoke(customWebElements, args);
    } catch (InvocationTargetException e) {
      throw e.getCause();
    }
  }
}
