import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import customFunction.getConfig as GetConfig

WebUI.openBrowser("")

String osName = CustomKeywords.'customFunction.getConfig.getOperatingSystem'()
String[] splitOS = osName.split()
osName = splitOS[0]
println(osName)

String[] browser = CustomKeywords.'customFunction.getConfig.getBrowserAndVersion'()
String browserName = browser[0]
String browserVersion = browser[1]
println(browserName + " " + browserVersion)

int[] screenResolution = CustomKeywords.'customFunction.getConfig.getScreenResolution'()
int screenWidth = screenResolution[0]
int screenHeight = screenResolution[1]

println(screenWidth + " X " + screenHeight)