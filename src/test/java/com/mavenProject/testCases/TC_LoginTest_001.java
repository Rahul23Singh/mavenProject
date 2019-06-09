package com.mavenProject.testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.mavenProject.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	@Test
	public void loginTest() {

		Logger.info("URL is opened");
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(username);
		Logger.info("username entered");
		lpg.setPassName(password);
		Logger.info("password entered");
		lpg.clickSubmit();
		Logger.info("Log In clicked");
		String title = driver.getTitle();
		System.out.println(title);
		if (driver.getTitle().equals("Facebook") == true) {
			Assert.assertTrue(true);
			Logger.info("Title verified");
		} else {
			Assert.assertFalse(true);
			Logger.info("Title failed");
		}
	}
}
