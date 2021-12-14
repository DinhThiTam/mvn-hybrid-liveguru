package pageUIs.user.liveGuru99;

public class BasePageUI {
	public static final String HEADER_ACCOUNT_CART_BY_TEXT = "//header[@id='header']//span[text()='%s']";
	public static final String MENU_LINK_BY_HEADERORFOOTER_AND_TEXT = "//div[contains(@class,'%s')]//a[text()='%s']";
	public static final String TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String BUTTON_TITLE = "//button[@title='%s']";
	public static final String MENUNAME_SIDEBAR = "//a[contains(string(),'%s')]";
	public static final String TEXT_DISPLAYED_BY_CLASS_AND_TEXT = "//div[@class='%s']//*[text()='%s']";
	public static final String PRODUCT_PRICE_BY_ID = "//div[@class='price-box']/span[@id='%s']";
	public static final String PRODUCT_NAME_BY_TEXT = "//h2[@class='product-name']/a[text()='%s']";
	public static final String ACTION_ITEM_BY_PRODUCTNAME_AND_ACTIONNAME = "//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']//*[text()='%s']";
	public static final String ROW_VALUE_BY_PRODUCTNAME_AND_TITLE = "//a[text()='%s']/parent::h2/parent::td/following-sibling::td/*[@title='%s']";
	public static final String MESSAGE_AT_TABLE_BY_PRODUCTNAME = "//a[text()='%s']/parent::h2/following-sibling::p";
	public static final String MESSAGE_DISPLAYED_BY_TEXT = "//ul[@class='messages']//span[text()='%s']";
}