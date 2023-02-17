import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class CustomFieldDecorator extends DefaultFieldDecorator {

  WebDriver webDriver;

  public CustomFieldDecorator(ElementLocatorFactory factory) {
    super(factory);
  }

  @Override
  public Object decorate(ClassLoader loader, Field field) {
    Class<?> fieldType = field.getType();

    if (CustomWebElement.class.isAssignableFrom(fieldType)) {
      return proxyForLocator(loader, factory.createLocator(field));
    }

    return super.decorate(loader, field);
  }

  private CustomWebElement proxyForCustomWebElement(ClassLoader loader, ElementLocator locator) {
    InvocationHandler handler = new LocatingCustomWebElementHandler(locator, webDriver);

    CustomWebElement proxy;
    proxy = (CustomWebElement) Proxy.newProxyInstance(loader,
        new Class[]{CustomWebElement.class}, handler);
    return proxy;
  }
}
