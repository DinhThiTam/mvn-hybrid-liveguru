package com.liveguru.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import commons.BaseTest;
import pageObject.user.liveGuru99.HomePO;
import pageObject.user.liveGuru99.LoginPO;
import pageObject.user.liveGuru99.MyAccountPO;
import pageObject.user.liveGuru99.PageGenerator;
import pageObject.user.liveGuru99.RegisterPO;
import utilities.DataUtil;

public class Common_01_Login_User extends BaseTest{
	WebDriver driver;
	public static String firstName, lastName,fullName, emailAddress, password;
	public static Set<Cookie> loginPageCookie;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		fakeData = DataUtil.getData();

		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		fullName = firstName + " " + lastName;
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		
		homePage = PageGenerator.getHomePO(driver);

		log.info("Pre-Condition - Step 02: Verify HomePage is displayed");
		homePage.isLogoAtHomePageDisplayed();

		log.info("Pre-Condition - Step 03: Click 'Account' menu on header");
		homePage.openToMenuAtHeaderByText(driver, "Account");

		log.info("Pre-Condition - Step 04: Open 'Register' page");
		homePage.clickToMenuLink(driver, "header", "Register");
		registerPage = PageGenerator.getRegisterPage(driver);
		homePage = PageGenerator.getHomePO(driver);
		
		log.info("Register_01 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "firstname",firstName);

		log.info("Register_01- Step 02: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "lastname",lastName);

		log.info("Register_01 - Step 03: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "email_address",emailAddress);

		log.info("Register_01 - Step 04: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "password",password);

		log.info("Register_01 - Step 05: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "confirmation",password);

		log.info("Register_01 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByTitle(driver, "Register");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("Register_01 - Step 07: Verify text dislayed after register successfully");
		verifyTrue(myAccountPage.isMessageDisplayed(driver,"Thank you for registering with Main Website Store."));
		
		log.info("Pre-Condition - Step 03: Click 'Account' menu on header");
		homePage.openToMenuAtHeaderByText(driver, "Account");
		
		log.info("Pre-Condition - Step 04: Open 'Log In' page");
		homePage.clickToMenuLink(driver, "header", "Log Out");
		
		log.info("Pre-Condition - Step 03: Click 'Account' menu on header");
		homePage.openToMenuAtHeaderByText(driver, "Account");

		log.info("Pre-Condition - Step 04: Open 'Log In' page");
		homePage.clickToMenuLink(driver, "header", "Log In");
		loginPage = PageGenerator.getLoginPage(driver);	
		
		log.info("Login_01 - Step 01: Enter valid info to 'Email Address' textbox");
		loginPage.enterToTextboxByID(driver, "email",emailAddress);

		log.info("Login_01 - Step 02: Enter valid info to 'Password' textbox");
		loginPage.enterToTextboxByID(driver, "pass",password);

		log.info("Login_01 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByTitle(driver, "Login");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("Login_01 - Step 04: Verify dashboard header text dislayed");
		verifyTrue(myAccountPage.isTextDisplayed(driver, "page-title","My Dashboard"));
		
		log.info("Login_01 - Step 05: Verify text dislayed on dashoard");
		verifyTrue(myAccountPage.isTextDisplayed(driver, "welcome-msg","Hello, " + fullName + "!"));
		
		loginPageCookie = myAccountPage.getAllCookies(driver);
		
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	
	HomePO homePage;
	RegisterPO registerPage;
	MyAccountPO myAccountPage;
	LoginPO loginPage;
	DataUtil fakeData;
	
}
