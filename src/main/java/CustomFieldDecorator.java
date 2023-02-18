import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class CustomFieldDecorator extends DefaultFieldDecorator {

  private WebDriver webDriver;

  public CustomFieldDecorator(WebDriver webDriver) {
    super(new DefaultElementLocatorFactory(webDriver));
    this.webDriver = webDriver;
  }

  public CustomFieldDecorator(ElementLocatorFactory factory) {
    super(factory);
  }

  @Override
  public Object decorate(ClassLoader loader, Field field) {
    Class<?> fieldType = field.getType();

    if (CustomWebElement.class.isAssignableFrom(fieldType)) {
      return proxyForCustomWebElement(loader, factory.createLocator(field));
    }

    Class<?> listType = getListType(field);
    if (listType != null) {
      return proxyForListCustomWebElement(loader, factory.createLocator(field));
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

  @SuppressWarnings("unchecked")
  private List<CustomWebElement> proxyForListCustomWebElement(ClassLoader loader,
      ElementLocator locator) {
    InvocationHandler handler = new LocatingCustomWebElementListHandler(locator, webDriver);

    List<CustomWebElement> proxy;
    proxy = (List<CustomWebElement>) Proxy.newProxyInstance(loader, new Class[]{List.class},
        handler);
    return proxy;
  }

  private Class<?> getListType(Field field) {
    if (!List.class.isAssignableFrom(field.getType())) {
      return null;
    }

    Type genericType = field.getGenericType();
    if (!(genericType instanceof ParameterizedType)) {
      return null;
    }
    Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
    return (Class<?>) listType;
  }
}
