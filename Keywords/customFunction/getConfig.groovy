package customFunction

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import java.awt.*

public class getConfig {
	@Keyword
	def getOperatingSystem () {
		String osName = System.getProperty('os.name')
		return osName
	}

	@Keyword
	def getBrowserAndVersion() {
		WebDriver driver = DriverFactory.getWebDriver()
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities()
		String[] browser = new String[2]
		browser[0] = caps.getBrowserName().capitalize()
		browser[1] = caps.getVersion()
		//String browserName = caps.getBrowserName().capitalize()
		//String browserVersion = caps.getVersion()
		//println browser[0] + ' ' + browser[1]
		
		return browser
	}

	@Keyword
	def getScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize()
		int[] screenResolution = new int[2]
		screenResolution[0] = screenSize.width
		screenResolution[1] = screenSize.height
		//println screenWidth + 'x' + screenHeight
		
		return screenResolution
	}
}
