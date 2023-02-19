import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CustomWebElementImpl implements CustomWebElement {
    private final WebElement webElement;
    private final WebDriver webDriver;
    private Actions builder;

    public CustomWebElementImpl(WebElement webElement, WebDriver webDriver) {
        this.webElement = webElement;
        this.webDriver = webDriver;
        this.builder = new Actions(webDriver);
    }

    @Override
    public void hover() {
        builder.moveToElement(webElement).build().perform();
    }

    @Override
    public void click() {
        webElement.click();
    }

    @Override
    public void submit() {
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        webElement.clear();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return webElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return webElement.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return webElement.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return webElement.getScreenshotAs(target);
    }
}
