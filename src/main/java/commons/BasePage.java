package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.user.liveGuru99.BasePageUI;
import pageUIs.user.liveGuru99.ShoppingCartPageUI;




/**
 * @author Admin
 *
 */
public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();

	}

	public String getPageSouce(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void clearTextbox (WebDriver driver, String locator) {
		getElement(driver, locator).clear();
	}

	public Alert waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.accept();
		sleepInsecond(2);
	}

	public void cancelAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		return alert.getText();
	}

	public void senkeyToAlert(WebDriver driver, String value) {
		alert = waitAlertPresence(driver);
		alert.sendKeys(value);
	}

	public void switchWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {

			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String pageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle().trim();
			if (actualTitle.equals(pageTitle)) {
				break;
			}
		}
	}

	public void closeAlltabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
	}

	public void sleepInsecond(long shortTimeoutInsecond) {
		try {
			Thread.sleep(shortTimeoutInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public WebElement getElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(locator, params)));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}
	
	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void clickToElement(WebDriver driver, String locator) {
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInsecond(2);
		} else {
		getElement(driver, locator).click();
		}
	}
	
	public void clickToElement(WebDriver driver, String locator, String... params) {
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, getDynamicLocator(locator, params));
			sleepInsecond(2);
		} else {
		getElement(driver, getDynamicLocator(locator, params)).click();
		}		
	}

	public void senkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}
	
	public void senkeyToElement(WebDriver driver, String locator, String value, String...params) {
		locator = getDynamicLocator(locator, params);
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public int getSizeElements(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}
	
	public int getSizeElements(WebDriver driver, String locator, String...params) {
		return getElements(driver, getDynamicLocator(locator, params)).size();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();	
	}
	
	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}
	
	public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public void selectDropdownByText(WebDriver driver, String locator, String itemText, String... params) {
		locator = getDynamicLocator(locator, params);
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public String getSelectedItemDropdown(WebDriver driver, String locator){
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDropdown(WebDriver driver, String locator, String... params){
		locator = getDynamicLocator(locator, params);
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInsecond(1);

		explicitWait = new WebDriverWait(driver, shortTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInsecond(1);

				item.click();
				sleepInsecond(1);
				break;
			}
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElement(driver, locator).getAttribute(attributeName);
	}
	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).getText().trim();
	}
	
	public String getElementCss(WebDriver driver, String locator, String cssValue) {
		return getElement(driver, locator).getCssValue(cssValue);
	}
	
	public String getElementCss(WebDriver driver, String locator, String cssValue, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElement(driver, locator).getCssValue(cssValue);
	}
	
	public String convertRgbaToHex(WebDriver driver, String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public void checkTheCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	public void checkTheCheckboxOrRadio(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			//Displayed: Visible on UI + In DOM
			//Undisplay: Invisible on UI + In DOM
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//Undisplayed + not in DOM + Invisible on UI
			return false;
		}
	}
	
	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			System.out.println("Element not in DOM and not visible on UI");
			System.out.println("End time " + new Date().toString());
			return true;		
		}else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/dislpayed on UI");
			System.out.println("End time " + new Date().toString());
			return true;
		}else {
			System.out.println("Element in DOM and visible on UI");
			return false;
		}
		
	}
	
	public void overrideGlobalTimeout (WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		
	}
	
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).isSelected();
	}
	
	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}
	
	public WebDriver switchToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}
	
	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}
	
	public void hoverToElement(WebDriver driver, String locator, String... params) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, getDynamicLocator(locator, params))).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}
	
	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator),getElement(driver, targetLocator)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();	
	}
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String...params) {
		action = new Actions(driver);
		locator = getDynamicLocator(locator, params);
		action.sendKeys(getElement(driver, locator), key).perform();	
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}
	
	public void scrollToElement(WebDriver driver, String locator, String... params ) {
		locator = getDynamicLocator(locator, params);
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));	
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));	
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));	
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));	
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));	
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));	
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));	
	}
	
	//LiveGuru99
	public void openToMenuAtHeaderByText(WebDriver driver, String menuText) {
		waitForElementClickable(driver, BasePageUI.HEADER_ACCOUNT_CART_BY_TEXT, menuText);
		clickToElement(driver, BasePageUI.HEADER_ACCOUNT_CART_BY_TEXT, menuText);
	}
	

	public void clickToMenuLink(WebDriver driver, String positionName, String menuText) {
		waitForElementClickable(driver, BasePageUI.MENU_LINK_BY_HEADERORFOOTER_AND_TEXT, positionName, menuText);
		clickToElement(driver, BasePageUI.MENU_LINK_BY_HEADERORFOOTER_AND_TEXT,positionName, menuText);
		
	}
	
	public void enterToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		senkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxID);
		
	}
	
	public void clickToButtonByTitle(WebDriver driver, String buttonTitle) {
		waitForElementClickable(driver, BasePageUI.BUTTON_TITLE, buttonTitle);
		clickToElement(driver, BasePageUI.BUTTON_TITLE, buttonTitle);
		
	}
	
	public void OpenMenuAtSidebar(WebDriver driver, String sidebarName) {
		waitForElementClickable(driver, BasePageUI.MENUNAME_SIDEBAR, sidebarName);
		clickToElement(driver, BasePageUI.MENUNAME_SIDEBAR, sidebarName);
		
	}
	

	public String getTextboxValueByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxID);
	}
	

	public boolean isTextDisplayed(WebDriver driver, String classText, String valueText) {
		waitForElementVisible(driver, BasePageUI.TEXT_DISPLAYED_BY_CLASS_AND_TEXT, classText, valueText);
		return isElementDisplayed(driver, BasePageUI.TEXT_DISPLAYED_BY_CLASS_AND_TEXT, classText, valueText);
	}
	
	public String getProductPrice(WebDriver driver, String productID) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_PRICE_BY_ID, productID);
		return getElementText(driver, BasePageUI.PRODUCT_PRICE_BY_ID, productID);
	}
	
	public void openProductDetail(WebDriver driver, String productText) {
		waitForElementClickable(driver, BasePageUI.PRODUCT_NAME_BY_TEXT, productText);
		clickToElement(driver, BasePageUI.PRODUCT_NAME_BY_TEXT, productText);
		
	}
	
	public void clickToActionByProductNameAndActionName(WebDriver driver, String productName, String actionName) {
		waitForElementClickable(driver, BasePageUI.ACTION_ITEM_BY_PRODUCTNAME_AND_ACTIONNAME, productName, actionName);
		clickToElement(driver, BasePageUI.ACTION_ITEM_BY_PRODUCTNAME_AND_ACTIONNAME, productName, actionName);
	}
	

	public void enterToTextboxByProductNameAndTitle(WebDriver driver, String productName, String title, String value) {
		waitForElementVisible(driver, BasePageUI.ROW_VALUE_BY_PRODUCTNAME_AND_TITLE, productName, title);
		senkeyToElement(driver, BasePageUI.ROW_VALUE_BY_PRODUCTNAME_AND_TITLE, value, productName, title);
		
	}
	
	public void clickToButtonByProductNameAndTitle(WebDriver driver, String productName, String title) {
		waitForElementClickable(driver, BasePageUI.ROW_VALUE_BY_PRODUCTNAME_AND_TITLE, productName, title);
		clickToElement(driver, BasePageUI.ROW_VALUE_BY_PRODUCTNAME_AND_TITLE, productName, title);
		
	}
	
	public String getErrorMessageInTableDisplayedByProductName(WebDriver driver, String productName) {
		waitForElementVisible(driver, BasePageUI.MESSAGE_AT_TABLE_BY_PRODUCTNAME, productName);
		return getElementText(driver, BasePageUI.MESSAGE_AT_TABLE_BY_PRODUCTNAME, productName);
	}

	public boolean isMessageDisplayed(WebDriver driver, String messageText) {
		waitForElementVisible(driver, BasePageUI.MESSAGE_DISPLAYED_BY_TEXT, messageText);
		return isElementDisplayed(driver, BasePageUI.MESSAGE_DISPLAYED_BY_TEXT, messageText);
	}
	
	public boolean isProductInfoInTableDisplayedByIMGProductnamePriceSKU(WebDriver driver, String imgSRC,
			String productName, String price, String sku) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_INFO_IN_TABLE_BY_IMG_PRODUCTNAME_PRICE_SKU, imgSRC,productName,price,sku);
		return isElementDisplayed(driver, BasePageUI.PRODUCT_INFO_IN_TABLE_BY_IMG_PRODUCTNAME_PRICE_SKU, imgSRC,productName,price,sku);
	}
	
	public void enterToTextAreaByID(WebDriver driver, String textareaID, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTAREA_BY_ID, textareaID);
		senkeyToElement(driver, BasePageUI.TEXTAREA_BY_ID, value, textareaID);
	}
	
	public void clickToLinkInProductDeatailsPage(WebDriver driver, String className, String linkText) {
		waitForElementClickable(driver, BasePageUI.LINK_PRODUCT_INFO_IN_PRODUCT_DETAIL_BY_CLASS_AND_TEXT, className, linkText);
		clickToElement(driver,BasePageUI.LINK_PRODUCT_INFO_IN_PRODUCT_DETAIL_BY_CLASS_AND_TEXT, className, linkText);
	}
	
	public boolean isErrorMessageDisplayedByContainsIDAndText(WebDriver driver, String containsID, String textMessage) {
		waitForElementVisible(driver, BasePageUI.MESSAGE_ERROR_BY_ID_AND_TEXT, containsID, textMessage);
		return isElementDisplayed(driver, BasePageUI.MESSAGE_ERROR_BY_ID_AND_TEXT, containsID, textMessage);
	}
	
	public void checkToRadioButtonByID(WebDriver driver, String radioID) {
		waitForElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_ID, radioID);
		checkTheCheckboxOrRadio(driver, BasePageUI.RADIO_BUTTON_BY_ID, radioID);	
	}
	
	public void selectItemInDropdownByID(WebDriver driver, String dropdownID, String value) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		selectDropdownByText(driver, BasePageUI.DROPDOWN_BY_ID, value, dropdownID);
		
	}
	
	public String getSelectItemInDropdownByID(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		return getSelectedItemDropdown(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
	}
	
	public void clickToButtonContinueByOnclick(WebDriver driver, String onclick) {
//		scrollToElement(driver, BasePageUI.CONTINUE_BUTTON_BY_ONCLICK, onclick);
		waitForElementClickable(driver, BasePageUI.CONTINUE_BUTTON_BY_ONCLICK, onclick);
		clickToElement(driver, BasePageUI.CONTINUE_BUTTON_BY_ONCLICK, onclick);
		
	}
	

	public void clickToButtonAdminByTitle(WebDriver driver, String buttonTitle) {
		waitForElementClickable(driver, BasePageUI.BUTTON_ADMIN_BY_TITLE, buttonTitle);
		clickToElement(driver, BasePageUI.BUTTON_ADMIN_BY_TITLE, buttonTitle);
	}
	
	public boolean isInfoAccountAtTableDisplayed(WebDriver driver, String username, String emailAddress) {
		waitForElementVisible(driver, BasePageUI.ROW_VALUE, username, emailAddress);
		return isElementDisplayed(driver, BasePageUI.ROW_VALUE, username, emailAddress);
	}
	
	
	
	



	

	
	
	private Alert alert;
	private WebDriverWait explicitWait;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
	
	
}
