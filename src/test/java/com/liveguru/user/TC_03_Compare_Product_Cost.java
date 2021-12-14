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

public class TC_03_Compare_Product_Cost extends BaseTest {
	WebDriver driver;
	String costProduct, costProductDetail;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);

		homePage = PageGenerator.getHomePO(driver);

		log.info("Pre-Condition - Step 02: Verify HomePage is displayed");
		homePage.isLogoAtHomePageDisplayed();

		log.info("Pre-Condition - Step 04: Open 'Register' page");
		homePage.clickToMenuLink(driver, "header", "Mobile");
		mobilePage = PageGenerator.getMobilePage(driver);	
	}

	@Test
	public void Login_01_Login_To_System() {
		log.info("Pre-Condition - Step 04: Get cost of Sony Experia mobile");
		costProduct= mobilePage.getProductPrice(driver,"product-price-1");
		
		log.info("Pre-Condition - Step 04: Click on Sony Experia detail");
		mobilePage.openProductDetail(driver, "Sony Xperia");
		
		log.info("Pre-Condition - Step 04: Get cost of Sony Experia mobile from detail page");
		costProductDetail= mobilePage.getProductPrice(driver,"product-price-1");
		
		log.info("Pre-Condition - Step 04: Compare value product is equal");
		verifyEquals(costProductDetail, costProduct);
	}
	

	@Parameters("browser")
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	HomePO homePage;
	MobilePO mobilePage;
	
}
