package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.user.liveGuru99.HomePO;
import pageObject.user.liveGuru99.PageGenerator;
import pageObject.user.liveGuru99.RegisterPO;

public class TC_01_Register extends BaseTest {
	WebDriver driver;
	String firstName, lastName, emailAddress, password;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "automationfc@gmail.com";
		password = "123456";

		homePage = PageGenerator.getHomePO(driver);

		log.info("Pre-Condition - Step 02: Verify HomePage is displayed");
		homePage.isLogoAtHomePageDisplayed();

		log.info("Pre-Condition - Step 03: Click 'Account' menu on header");
		homePage.openToMenuAtHeaderByText(driver, "Account");
		registerPage = PageGenerator.getRegisterPage(driver);

		log.info("Pre-Condition - Step 04: Open 'Register' page");
		registerPage.clickToMenuLink(driver, "header", "Register");
	}

	@Test
	public void Register_01_Success_To_System() {
		log.info("Register_02 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "firstname",firstName);

		log.info("Register_02 - Step 02: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "lastname",lastName);

		log.info("Register_02 - Step 03: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "email_address",emailAddress);

		log.info("Register_02 - Step 04: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "password",password);

		log.info("Register_02 - Step 05: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "confirmation",password);

		log.info("Register_02 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByTitle(driver, "Register");
	}

	HomePO homePage;
	RegisterPO registerPage;
}
