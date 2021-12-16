package com.liveguru.admin;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.liveguru.user.TC_02_Login;

import commons.BaseTest;
import pageObject.user.liveGuru99.CustomerAdminPO;
import pageObject.user.liveGuru99.HomePO;
import pageObject.user.liveGuru99.LoginAdminPO;
import pageObject.user.liveGuru99.LoginPO;
import pageObject.user.liveGuru99.MyAccountPO;
import pageObject.user.liveGuru99.PageGenerator;
import pageObject.user.liveGuru99.ProductDetailsPO;
import pageObject.user.liveGuru99.RegisterPO;
import pageObject.user.liveGuru99.ReviewPO;
import pageObject.user.liveGuru99.ShoppingCartPO;
import pageObject.user.liveGuru99.TVPO;
import utilities.DataUtil;

public class TC_01_Check_Account_Created_Success extends BaseTest {
	WebDriver driver;
	public static String firstName, lastName,fullName, emailAddress, password;
	
	@Parameters({ "browser", "urlUser", "urlAdmin"})
	@BeforeClass
	public void initBrowser(String browserName, String urlUser, String urlAdmin) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + urlUser + "'");
		driver = getBrowserDriver(browserName, urlUser);
		
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
		
		log.info("Pre-Condition - Step 05: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "firstname",firstName);

		log.info("Pre-Condition- Step 06: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "lastname",lastName);

		log.info("Pre-Condition - Step 07: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "email_address",emailAddress);

		log.info("Pre-Condition - Step 08: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "password",password);

		log.info("Pre-Condition - Step 09: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "confirmation",password);

		log.info("Pre-Condition - Step 10: Click to 'Register' button");
		registerPage.clickToButtonByTitle(driver, "Register");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("Pre-Condition - Step 11: Verify text dislayed after register successfully");
		verifyTrue(myAccountPage.isMessageDisplayed(driver,"Thank you for registering with Main Website Store."));
		cleanBrowserAndDriver();
		
		log.info("Pre-Condition - Step 12: Open browser '" + browserName + "' and navigate '" + urlAdmin + "'");
		driver = getBrowserDriver(browserName, urlAdmin);
		loginAdminPage = PageGenerator.getLoginAdminPage(driver);
		
	}

	@Test
	public void  TC_01_Check_Account_Info_Created_Success() {
		log.info("TC_01 - Step 01: Enter to 'User Name' textbox");
		loginAdminPage.enterToTextboxByID(driver, "username", "user01");
		
		log.info("TC_01 - Step 02: Enter to 'Password' textbox");
		loginAdminPage.enterToTextboxByID(driver, "login", "guru99com");
		
		log.info("TC_01 - Step 03: Click to 'Log In' button");
		loginAdminPage.clickToButtonAdminByTitle(driver, "Login");
		customerAdminPage = PageGenerator.getCustomerAdminPage(driver);
		
		log.info("TC_01 - Step 04: Close popup");
		customerAdminPage.clickToIconCloseInPopup();
		
		log.info("TC_01 - Step 05: Verify the account created on the user site is displayed on the admin site");
		verifyTrue(customerAdminPage.isInfoAccountAtTableDisplayed(driver, fullName, emailAddress));
		
	}
	

	@Parameters("browser")
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	HomePO homePage;
	RegisterPO registerPage;
	MyAccountPO myAccountPage;
	DataUtil fakeData;
	LoginAdminPO loginAdminPage;
	CustomerAdminPO customerAdminPage;
}
