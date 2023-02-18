import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LocatingCustomWebElementHandler implements InvocationHandler {

  private final ElementLocator locator;
  private final WebDriver webDriver;

  public LocatingCustomWebElementHandler(ElementLocator locator, WebDriver webDriver) {
    this.locator = locator;
    this.webDriver = webDriver;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    WebElement webElement = locator.findElement();
    CustomWebElementImpl customWebElement = new CustomWebElementImpl(webElement, webDriver);

    try {
      return method.invoke(customWebElement, args);
    } catch (InvocationTargetException e) {
      throw e.getCause();
    }
  }
}
