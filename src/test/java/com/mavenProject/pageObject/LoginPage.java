package com.mavenProject.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	@CacheLookup
	WebElement unTxtBx;
	@FindBy(id = "pass")
	@CacheLookup
	WebElement pwdTxtBx;
	@FindBy(id = "u_0_2")
	@CacheLookup
	WebElement logIn;

	public void setUserName(String userName) {
		unTxtBx.sendKeys(userName);
	}

	public void setPassName(String passName) {
		pwdTxtBx.sendKeys(passName);
	}

	public void clickSubmit() {
		logIn.click();
	}

}
