package com.liveguru.user;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageObject.user.liveGuru99.CompareProductsListPO;
import pageObject.user.liveGuru99.HomePO;
import pageObject.user.liveGuru99.MobilePO;
import pageObject.user.liveGuru99.PageGenerator;
import pageObject.user.liveGuru99.ShoppingCartPO;

public class TC_06_Compare_Two_Products extends BaseTest {
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
	public void TC_01_Verify_Compare_Products() {		
		log.info("TC_01 - Step 01: Click on 'Add to Compare' for IPhone mobile");
		mobilePage.clickToActionByProductNameAndActionName(driver, "IPhone", "Add to Compare");
		
		log.info("TC_01 - Step 02: Verify message is displayed");
		mobilePage.isMessageDisplayed(driver, "The product IPhone has been added to comparison list.");
		
		log.info("TC_01 - Step 03: Click on 'Add to Compare' for Sony Experia mobile");
		mobilePage.clickToActionByProductNameAndActionName(driver, "Sony Xperia", "Add to Compare");
		
		log.info("TC_01 - Step 04: Verify message is displayed");
		mobilePage.isMessageDisplayed(driver, "The product Sony Xperia has been added to comparison list.");
		
		log.info("TC_01 - Step 05: Get the active Window ID");
		String parentID = driver.getWindowHandle();
		
		log.info("TC_01 - Step 06: Click on 'Compare' button");
		mobilePage.clickToButtonByTitle(driver, "Compare");
		compareProductPage = PageGenerator.getCompareProductListPage(driver);
		 
		log.info("TC_01 - Step 07: Switch sub-windown by windown ID");
		compareProductPage.switchWindowByID(driver, parentID);
			
		log.info("TC_01 - Step 08: Verify popup window is displayed");
		compareProductPage.sleepInsecond(3);
		verifyEquals(compareProductPage.getPageURL(driver), "http://live.techpanda.org/index.php/catalog/product_compare/index/");
		
		log.info("TC_01 - Step 09: Verify Heading 'Compare Products' is displayed");
		verifyTrue(compareProductPage.isHeadingDisplayed());
		
		log.info("TC_01 - Step 10: Verify mobile info 'Sony experia' is displayed");
		verifyTrue(compareProductPage.isProductInfoInTableDisplayedByIMGProductnamePriceSKU(driver,"xperia.jpg","Sony Xperia", "$100.00", "MOB001"));
		
		log.info("TC_01 - Step 11: Verify mobile info 'Iphone' is displayed");
		verifyTrue(compareProductPage.isProductInfoInTableDisplayedByIMGProductnamePriceSKU(driver,"iphone.png","IPhone", "$500.00", "MOB0002"));
		
		log.info("TC_01 - Step 12: Verify mobile info 'Iphone' is displayed");
		compareProductPage.closeAlltabWithoutParent(driver, parentID);
		
		log.info("TC_01 - Step 13: Switch to parent window");
		driver.switchTo().window(parentID);
		
		log.info("TC_01 - Step 14: Verify URL parent window is displayed");
		verifyEquals(compareProductPage.getPageURL(driver), "http://live.techpanda.org/index.php/mobile.html");
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
	CompareProductsListPO compareProductPage;
	
}
