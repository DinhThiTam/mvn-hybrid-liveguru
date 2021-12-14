package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageObject.user.liveGuru99.HomePO;
import pageObject.user.liveGuru99.LoginPO;
import pageObject.user.liveGuru99.MyAccountPO;
import pageObject.user.liveGuru99.PageGenerator;

public class TC_02_Login extends BaseTest {
	WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);

		homePage = PageGenerator.getHomePO(driver);

		log.info("Pre-Condition - Step 02: Verify HomePage is displayed");
		homePage.isLogoAtHomePageDisplayed();
		
		log.info("Pre-Condition - Step 03: Click 'Account' menu on header");
		homePage.openToMenuAtHeaderByText(driver, "Account");

		log.info("Pre-Condition - Step 04: Open 'Register' page");
		homePage.clickToMenuLink(driver, "header", "Log In");
		loginPage = PageGenerator.getLoginPage(driver);	
	}

	@Test
	public void Login_01_Login_To_System() {
		log.info("Login_01 - Step 01: Enter valid info to 'Email Address' textbox");
		loginPage.enterToTextboxByID(driver, "email",TC_01_Register_Success.emailAddress);

		log.info("Login_01 - Step 02: Enter valid info to 'Password' textbox");
		loginPage.enterToTextboxByID(driver, "pass",TC_01_Register_Success.password);

		log.info("Login_01 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByTitle(driver, "Login");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("Login_01 - Step 04: Verify dashboard header text dislayed");
		verifyTrue(myAccountPage.isTextDisplayed(driver, "page-title","My Dashboard"));
		
		log.info("Login_01 - Step 05: Verify text dislayed on dashoard");
		verifyTrue(myAccountPage.isTextDisplayed(driver, "welcome-msg","Hello, " + TC_01_Register_Success.fullName + "!"));
	}
	

	@Parameters("browser")
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	HomePO homePage;
	MyAccountPO myAccountPage;
	LoginPO loginPage;
	
}
