package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageObject.user.liveGuru99.HomePO;
import pageObject.user.liveGuru99.MobilePO;
import pageObject.user.liveGuru99.PageGenerator;
import pageObject.user.liveGuru99.ShoppingCartPO;

public class TC_05_Maximum_Quantity_Add_To_Cart extends BaseTest {
	WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("TC_01 - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);

		homePage = PageGenerator.getHomePO(driver);

		log.info("TC_01 - Step 02: Verify HomePage is displayed");
		homePage.isLogoAtHomePageDisplayed();

		log.info("TC_01 - Step 03: Open 'Mobile' menu");
		homePage.clickToMenuLink(driver, "header", "Mobile");
		mobilePage = PageGenerator.getMobilePage(driver);	
	}

	@Test
	public void TC_01_Check_Allowed_Quantity() {		
		log.info("TC_01 - Step 01: Click on 'ADD TO CART' for Sony Experia mobile");
		mobilePage.clickToActionByProductNameAndActionName(driver, "Sony Xperia", "Add to Cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
	
		log.info("TC_01 - Step 02: Change 'QTY' value is 501");
		shoppingCartPage.enterToTextboxByProductNameAndTitle(driver, "Sony Xperia", "Qty", "501");
		
		log.info("TC_01 - Step 03: Click to 'Update' button");
		shoppingCartPage.clickToButtonByProductNameAndTitle(driver,"Sony Xperia", "Update");
		
		log.info("TC_01 - Step 04: Verify error message is displayed");
		verifyTrue(shoppingCartPage.isMessageDisplayed(driver, "Some of the products cannot be ordered in requested quantity."));
		
		log.info("TC_01 - Step 05: Verify error message in table is displayed");
		verifyEquals(shoppingCartPage.getErrorMessageInTableDisplayedByProductName(driver, "Sony Xperia"), "* The maximum quantity allowed for purchase is 500.");
		
		log.info("TC_01 - Step 06: Click to 'Empty Cart' link");
		shoppingCartPage.clickToButtonByTitle(driver, "Empty Cart");
		
		log.info("TC_01 - Step 07: Verify message is displayed");
		verifyTrue(shoppingCartPage.isShoppingCartEmptyMessageDisplayed());
	}
	

	@Parameters("browser")
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	HomePO homePage;
	MobilePO mobilePage;
	ShoppingCartPO shoppingCartPage;
	
}
