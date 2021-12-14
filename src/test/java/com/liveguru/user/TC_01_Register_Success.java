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
import pageObject.user.liveGuru99.RegisterPO;
import utilities.DataUtil;

public class TC_01_Register_Success extends BaseTest {
	WebDriver driver;
	public static String firstName, lastName,fullName, emailAddress, password;

	@Parameters({ "browser", "url" })
	@BeforeClass
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
	}

	@Test
	public void  Register_01_Success_To_System() {
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
		verifyTrue(myAccountPage.isErrorMessageDisplayed(driver,"Thank you for registering with Main Website Store."));
		
	}
	
	@Test
	public void Register_02_Check_Infomation_Registered() {
		log.info("Register_02 - Step 01: Open'Account Information' menu at sidebar");
		myAccountPage.OpenMenuAtSidebar(driver, "Account Information");

		log.info("My_Account_01 - Step 11: Verify firstname infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "firstname", "value"), firstName);
		
		log.info("My_Account_01 - Step 12: Verify lastname infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "lastname", "value"), lastName);
		
		log.info("My_Account_01 - Step 12: Verify lastname infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "email", "value"), emailAddress);
	}
	

	@Parameters("browser")
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	HomePO homePage;
	MyAccountPO myAccountPage;
	RegisterPO registerPage;
	LoginPO loginPage;
	DataUtil fakeData;
}
