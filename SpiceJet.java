package com.day3;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;


public class SpiceJet {

	private static WebDriver driver;
	private static ChromeOptions options;
	private static final Logger logger = Logger.getLogger(SpiceJet.class);

	public SpiceJet() {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
	}

	public void launchUrl(String url) {
		driver.get(url);
	}

	public void setDimension(int width, int height) {
		Dimension size = new Dimension(width, height);
		driver.manage().window().setSize(size);
	}

	public void verifyElementText(By locator, String expectedText) {
		WebElement element = driver.findElement(locator);
		String actualText = element.getText();
		assertEquals("The text does not match!", expectedText, actualText);
		logger.info("Current title: " + driver.getTitle());
	}

	public void closeDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public static void main(String[] args) {
		SpiceJet spiceJet = new SpiceJet();
		try {
			spiceJet.launchUrl("https://www.spicejet.com/");
			spiceJet.setDimension(1200, 800);
			spiceJet.verifyElementText(By.xpath("//div[text()='Flights']"), "Flights");
		} finally {
			spiceJet.closeDriver();
		}
	}
}
