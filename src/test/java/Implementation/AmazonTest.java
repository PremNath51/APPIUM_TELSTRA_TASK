package Implementation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utility.CommonFunctions;
import Utility.ExtentRepotEx;
import Utility.ImagecaptureConfig;
import Utility.LoginUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AmazonTest extends ExtentRepotEx
{
	  static WebDriver wd;
	  public AndroidDriver driver;
	  public RemoteWebDriver driver2;
   
	  DesiredCapabilities capabilities=DesiredCapabilities.android();
	  
      int scrollTimeout = 700;
  	  int header = 98;
  	  int footer = 0;
  	  float dpr = 2;

  	  String Native = "NATIVE_APP";
  	  String Webview = "WEBVIEW_1";
  	  
  	  CustomerSignin acnt;
  	  ListingItem srch;
  	  CreditCard card;
	  
      ImagecaptureConfig img = new ImagecaptureConfig();
	  CommonFunctions cmn = new CommonFunctions();
	  SoftAssert soft=new SoftAssert();
    
	@BeforeTest
    public void baseClass() throws InterruptedException, IOException 

    {
	  //Location of the application
   	    File app = new File("/Users/[premnathrajasekaran]/Library/Caches/com.apple.Safari”, "Amazon_shopping.apk");
   	 
   	    capabilities.setCapability("device","Android");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
   	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SAMSUNG GALAXY S10");
   	    capabilities.setCapability("app", app.getAbsolutePath());

   	    capabilities.setCapability("platformName","Android");
        capabilities.setCapability("UDID", "WSNNJR7654RFDSA7"); //In Command line search for adb devices to get the device details
	
   	    capabilities.setCapability(CapabilityType.VERSION,"9.0");
 	    capabilities.setCapability("orientation", "PORTRAIT");
			
   	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
   	    Thread.sleep(2700);
    	    
      //set the package name of the app
   	    capabilities.setCapability("app-package", "com.example.android.contactmanager-1");
   	  //set the Launcher activity name of the app
   	    capabilities.setCapability("app-activity", ".ContactManager");
   	    Thread.sleep(1700);
   	    
   	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
   	    Thread.sleep(37000);
 	     
   	    acnt = PageFactory.initElements(driver, CustomerSignin.class);
   	    srch = PageFactory.initElements(driver, ListingItem.class);
   	    card = PageFactory.initElements(driver, CreditCard.class);
   	    
     }
    
	// This Function is used to “Login to amazon mobile application"
	@Test(priority = 1, dataProvider = "Login Authentication")
    public void CustomerSignin(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		   try
		   {  
			     img.capturesnapshot();
			     img.reportsnapshotPass("Amazon Application Opened Successfully." );
			     
			    //Print message in extent report 
			     cmn.Info("Create a new Amazon login Account");
			     
			    //Call User login Page object 
			     acnt.SignIn(Mobile_Number, Password);
			     
			       WebDriverWait wait = new WebDriverWait (driver, 5540);
		  		   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Shop by Category')]")));
			     
		  		   
		  		    	if(driver.getPageSource().contains("Shop by Category"))
		  		    	{
		  		    		 img.capturesnapshot();
		  				     img.reportsnapshotPass("User successfully logined and able to view the amazon dashboard page." );
		  		    	}
   		         
 		   }  
		   catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAIL");
			   AssertJUnit.assertTrue("Login issue occured login amazon account", acnt.isDisplayed());
			   throw(e);
		   }
    }
	
	//This function will "search for an item and add to cart and purchase it”
	@Test(priority = 2, dataProvider = "Authentication")
    public void PurchaseProduct(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		   try
		   {  
			     cmn.Info("Search Item");
			     srch.ListingItem(Item_Name);
			     
			    //This string will find the TV from the serach list 
			     String name = "SAMSUNG 65 RU7100 Series 7 4K UHD HDR Smart LED TV UA65RU7100WXXY";
			     
			   //This string will store the selected TV name   
			     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(2)).scrollIntoView(new UiSelector().textContains(\""+name+"\").instance(0))").click();
		 		
			   //This string will store the Price of the TV and save
			     String price = driver.findElement(By.xpath("//*[@id='priceblock_dealprice']")).getText();
			     
			   //This string will store the Description the TV brand and details
			     @SuppressWarnings("unused")
				 String dscrptn = driver.findElement(By.xpath("//*[@id='featurebullets_feature_div']")).getText();
			     
			   //This string will store the selected item to click on the ADD to cart a item
			     
			     driver.findElement(By.xpath("//*[contains(text(), 'Add to Cart')]")).click();
			     Thread.sleep(1500);
			     
			        if(driver.getPageSource().contains("Added to cart"))
	  		    	{
	  		    		 img.capturesnapshot();
	  				     img.reportsnapshotPass("Item Added to Cart" );
	  		    	}
			        
			       // Action button Click on cart button
			         driver.findElement(By.xpath("//*[@id='nav-cart-count']")).click();
			         
			      // Action button Click on proceed to buy button click
			           driver.findElement(By.xpath("//*[contains(text(), 'Proceed to Buy')]")).click();
			           
			        // Action button Click on Use the exisitng or current address
			           driver.findElement(By.xpath("//*[contains(text(), 'Use this address')]")).click();
			           
			        // Action button Click on Continue button to proceed the item 
			           driver.findElement(By.xpath("//*[contains(text(), 'Continue')]")).click(); 
			           
			         // Action button Click on click on Credit/Debit card details
			           driver.findElement(By.xpath("//*[contains(text(), 'Add Debit/Credit/ATM Card')]")).click();
			           
			         //Verify Item name on Checkout page
			 		   String Itemname = "SAMSUNG 65 RU7100 Series 7 4K UHD HDR Smart LED TV UA65RU7100WXXY";
			 		   
			  		      //Verify Item the name before click on purchase the item 
			  		    	if(name.equals(Itemname))
			  		    	{
			  		    		 img.capturesnapshot();
			  				     img.reportsnapshotPass("Same item is selected from previous one" );
			  		    	}
			  		    	
			  		       //Verify the price on amaazon checkout page
					 		   String pricess = driver.findElement(By.xpath("//*[@id='price']")).getText();
					 		   
					  		      //Verify Item the price before click on purchase the item 
					  		    	if(pricess.equals(price))
					  		    	{
					  		    		 img.capturesnapshot();
					  				     img.reportsnapshotPass("Same item is selected from previous one" );
					  		    	}
			           
			          //Get the credit card details from Card payment page object
			           card.paymentType(Card_Number);
 		   }  
		   catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Failed : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAILED");
			   AssertJUnit.assertTrue("Error occured while item added to cart.", acnt.isDisplayed());
			   throw(e);
		   }
    }
	
	//This function will authenticate and return the object from excelsheet
	@DataProvider
    public String[][] Authentication() throws Exception
    {   
		
 	     String POS_LoginExcel=properties.getProperty("LoginExcel_DataPath");
 	     String[][] testObjArray = LoginUtils.getTableArray(POS_LoginExcel+"\\AmazonData.xlsx","Sheet1");
 	     return testObjArray;
 	     
    }
    
    @AfterClass
    public void closeBrowser() throws InterruptedException
    {
              driver.quit();
    }
}
