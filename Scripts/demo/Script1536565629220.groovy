import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.util.HashMap
import java.util.Set
import customFunction.writeExcel as writeExcel
import customFunction.getConfig as GetConfig

def myLuckyCheck = false
def loggedin = false
def openBrowser = false
int rowNum = 0
String osName = CustomKeywords.'customFunction.getConfig.getOperatingSystem'()
String[] splitOS = osName.split()
osName = splitOS[0]
if (osName.toLowerCase() == "mac") {
	input_Path = minput_Path
}

Loop:
while (myLuckyCheck != true) {
	if(openBrowser != true){
		WebUI.openBrowser('https://shop.circles.life/zero/login')
		openBrowser = true
		loggedin = false
	}
	
	WebUI.verifyElementVisible(findTestObject('button_facebook'))
	
	WebUI.click(findTestObject('button_facebook'))
	
	if (loggedin != true) {
		WebUI.switchToWindowIndex(1)
		
		WebUI.setText(findTestObject('input_email'), input_email)
		
		WebUI.setText(findTestObject('input_password'), input_password)
		
		WebUI.click(findTestObject('button_login'))
		
		WebUI.switchToWindowIndex(0)
		
		WebUI.waitForElementVisible(findTestObject('label_number'), 10)
		loggedin = true
	}
	
	if(loggedin == true){
		WebUI.waitForElementVisible(findTestObject('label_number'), 10)
	}
	
	def getNumber = WebUI.getText(findTestObject('label_number'))
	while (getNumber.trim() == "") {
		getNumber = WebUI.getText(findTestObject('label_number'))
	}
	//String[] getNumberList = getNumber.split("8768")
	//getNumber = getNumberList[1]
	println(getNumber)
	
	CustomKeywords.'customFunction.writeExcel.writeToExcel'(input_Path, rowNum, 1, getNumber)
	rowNum = ++rowNum
	
	//String[] arrayNumber = getNumber.split("")
	//println(arrayNumber)
	HashMap charCountMap = new HashMap()
	for(int i=0; i < getNumber.length(); i++){
		char c = getNumber.charAt(i)
		
		if(charCountMap.containsKey(c)){
			charCountMap.put(c, charCountMap.get(c) + 1)
		}else {
			charCountMap.put(c, 1)
		}
		for (Character duplicateNumber : charCountMap.keySet()) {
			if (charCountMap.get(duplicateNumber) >= 4){
				println("duplicate character : " + duplicateNumber + " ====== " + " count : " + charCountMap.get(duplicateNumber))
				//if (duplicateNumber == "8" || duplicateNumber == "6") {
					myLuckyCheck = true
					break Loop
				//}
			}
	   }
	}
	
	if(myLuckyCheck != true){
		if(WebUI.waitForElementVisible(findTestObject('button_logout'), 3)){
			WebUI.click(findTestObject('button_logout'))
		}else{
			openBrowser = false
			WebUI.closeBrowser()
		}
	}
}