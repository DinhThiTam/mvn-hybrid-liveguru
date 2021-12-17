package com.liveguru.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObject.user.liveGuru99.CustomerAdminPO;
import pageObject.user.liveGuru99.HomePO;
import pageObject.user.liveGuru99.LoginAdminPO;
import pageObject.user.liveGuru99.PageGenerator;

public class Common_01_Login_Admin extends BaseTest{
	WebDriver driver;
	public static Set<Cookie> loginPageCookie;

	@Parameters({ "browser", "urlUser", "urlAdmin"})
	@BeforeTest
	public void initBrowser(String browserName, String urlUser, String urlAdmin) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + urlAdmin + "'");
		driver = getBrowserDriver(browserName, urlAdmin);
		loginAdminPage = PageGenerator.getLoginAdminPage(driver);
		
		log.info("TC_01 - Step 01: Enter to 'User Name' textbox");
		loginAdminPage.enterToTextboxByID(driver, "username", "user01");
		
		log.info("TC_01 - Step 02: Enter to 'Password' textbox");
		loginAdminPage.enterToTextboxByID(driver, "login", "guru99com");
		
		log.info("TC_01 - Step 03: Click to 'Log In' button");
		loginAdminPage.clickToButtonAdminByTitle(driver, "Login");
		customerAdminPage = PageGenerator.getCustomerAdminPage(driver);
		
		log.info("TC_01 - Step 04: Verify the popup is displayed");
		verifyTrue(customerAdminPage.isPopupDisplayed());
		
		loginPageCookie = customerAdminPage.getAllCookies(driver);
		
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	
	HomePO homePage;
	CustomerAdminPO customerAdminPage;
	LoginAdminPO loginAdminPage;
	
}
