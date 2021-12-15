package pageUIs.user.liveGuru99;

public class ShoppingCartPageUI {
	public static final String SHOPPING_CART_EMPTY_MESSAGE = "//h1[text()='Shopping Cart is Empty']/parent::div/following-sibling::div/p[text()='You have no items in your shopping cart.']";
	public static final String FLATE_RATE_RADIO = "//label[contains(string(),'$5.00')]";
	public static final String GRAND_TOTAL = "//strong[text()='Grand Total']/parent::td/following-sibling::td/strong";
	public static final String PAGE_TITLE_ORDER_SUCCESS = "//h1[text()='Your order has been received.']";
	public static final String ORDERED_NUMBER = "//h2[@class='sub-title']/following-sibling::p[1]";
	
}
