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
import GlobalVariables.amazon_Var;
import GlobalVariables.amazon_Msgs;
import GlobalVariables.amz_Product;
import PageObjects.Amazon_Pg_Obj;

public class AmazonTest extends ExtentRepotEx
{
	//Initializing the variables
	
	  static WebDriver wd;
	  public static AndroidDriver driver;
	  public RemoteWebDriver remoteDriver;
	  DesiredCapabilities capabilities=DesiredCapabilities.android();
      int scrollTimeout = amazon_Var.scrollTimeout;
  	  int header = amazon_Var.header;
  	  int footer = amazon_Var.footer;
  	  float dpr = amazon_Var.dpr;
  	  CustomerSignin acnt;
  	  ListingItem srch;
  	  CreditCard card;
  	  String launch = amazon_Var.Launch_MG;
      ImagecaptureConfig img = new ImagecaptureConfig();
	  CommonFunctions cmn = new CommonFunctions();
	  SoftAssert soft=new SoftAssert();
    
	@BeforeTest
    public void baseClass() throws InterruptedException, IOException 

    {
		//Location of the amazon APK application
   	    File app = new File(amazon_Var.Amazon_PATH,amazon_Var.Amazon_APK);
   	    capabilities.setCapability(amazon_Var.Amazon_Device,amazon_Var.Amazon_Android);
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,amazon_Var.Amazon_Android);
   	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, amazon_Var.Amzon_Device_Name);
   	    capabilities.setCapability("app", app.getAbsolutePath());
   	    capabilities.setCapability(amazon_Var.Amzon_Plat_Name,amazon_Var.Amazon_Android);
        capabilities.setCapability(amazon_Var.Amzon_UDID, amazon_Var.Amzon_UDID_VAL); //In Command line search for adb devices to get the device details
   	    capabilities.setCapability(CapabilityType.VERSION,"9.0");
 	    capabilities.setCapability("orientation", "PORTRAIT");	
   	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
   	    Thread.sleep(2700);
   	    capabilities.setCapability(amazon_Var.Amzon_APP_PACK, amazon_Var.Amzon_APP_PACK_VAL);
   	    capabilities.setCapability(amazon_Var.Amzon_APP_ACT, amazon_Var.Amzon_APP_ACT_VAL);
   	    Thread.sleep(1700);
   	    driver = new AndroidDriver(new URL(amazon_Var.Amzon_AndroidDriver_URL), capabilities);
   	    Thread.sleep(37000);
   	    acnt = PageFactory.initElements(driver, CustomerSignin.class);
   	    srch = PageFactory.initElements(driver, ListingItem.class);
   	    card = PageFactory.initElements(driver, CreditCard.class);
    }
	
	// This Test case will execute and install the Amazon.APK from the local path 
	@Test(priority = 1)
    public void LaunchAmazonApp() throws Exception
    { 
		try
		{
			if(launch.contains(amz_Product.MAIN_CATEG))
			{
			 img.capturesnapshot();
		     img.reportsnapshotPass(amazon_Msgs.LAUNCH_SUCCESS);
			}
		}
		 catch(Exception e)
		 {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAIL");
			   AssertJUnit.assertTrue(amazon_Msgs.APK_ERR, acnt.isDisplayed());
			   throw(e);
         }
    }
    
	// This Test case is used to “Login to amazon mobile application"
	@Test(priority = 2, dataProvider = "Authentication")
    public void CustomerSignin(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		   try
		   {  
			     img.capturesnapshot();
			     img.reportsnapshotPass(amazon_Msgs.APP_OPEN);
			    //Print message in extent report 
			     cmn.Info(amazon_Msgs.APP_CREATE);
			    //Call User login Page object 
			     acnt.CustomerSignin(Mobile_Number, Password);
			     WebDriverWait wait = new WebDriverWait (driver, 5540);
				    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(amz_Product.PROD_CATEG)));
				    	if(driver.getPageSource().contains(amz_Product.SHP_CATEG))
				    	{
				    		 img.capturesnapshot();
						     img.reportsnapshotPass(amz_Product.SHP_CATEG_SUCC);
				    	}
			     img.capturesnapshot();
			     img.reportsnapshotPass(amazon_Msgs.LOGIN_SUCCESS);
 		   }  
		   catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAIL");
			   AssertJUnit.assertTrue(amazon_Msgs.LOGIN_FAIL, acnt.isDisplayed());
			   throw(e);
		   }
    }
	
	// This Test case will search for a 50-65 -inch TV from the TV from the search result
		@Test(priority = 3, dataProvider = "Authentication")
	    public void ProductSearch_Result(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
	    { 
			try
			{
			// Product data retrive from Page Object model
				Amazon_Pg_Obj.searchProductSizeItems();
				 //This return string will find the 50-65 TV from the search list 
			 if(Amazon_Pg_Obj.searchProductSizeItems() != null)
			 {
	  		 img.capturesnapshot();
			 img.reportsnapshotPass(amz_Product.SELC_SIZE_PROD);
			 }
			}
			 catch(Exception e)
			   {
				   e.getMessage();
				   img.capturesnapshot_Fail();
				   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
				   Assert.assertFalse(false, "FAIL");
				   AssertJUnit.assertTrue(amz_Product.FIND_SIZE_PROD, acnt.isDisplayed());
				   throw(e);
			   }
	    }
    
	// This Test case will search for a 65-inch TV from the TV from and select from the result
	@Test(priority = 4, dataProvider = "Authentication")
    public void SelectProductSearchResult(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		try
		{
	     
	  // Product data reterive from Page Object model & This string will find the TV from the search list 
	     String serachName = Amazon_Pg_Obj.selectProductSearchItem();
	   //This string will store and select the selected TV name   
	     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(2)).scrollIntoView(new UiSelector().textContains(\""+serachName+"\").instance(0))").click();
  		 img.capturesnapshot();
		 img.reportsnapshotPass(amz_Product.SELC_PROD);
		}
		 catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAIL");
			   AssertJUnit.assertTrue(amz_Product.FIND_PROD, acnt.isDisplayed());
			   throw(e);
		   }
    }
 
	@Test(priority = 5, dataProvider = "Authentication")
    public void ViewProductDetailPageWithAddCart(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		   try
		   {
			// Product data reterive from Page Object model 
            Amazon_Pg_Obj.viewProductDetailsPage();
            //This below functionality worked for add a item to cart
            Amazon_Pg_Obj.addToCart();
            if(Amazon_Pg_Obj.viewProductDetailsPage() != null & Amazon_Pg_Obj.addToCart() == true)
            {
             img.capturesnapshot();
       		 img.reportsnapshotPass(amz_Product.View_PROD_ADD_CART);
            }
		     Thread.sleep(1500);
		   }
		   catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAIL");
			   AssertJUnit.assertTrue(amz_Product.FIND_PROD, acnt.isDisplayed());
			   throw(e);
		   }
    }
	
	@Test(priority = 6, dataProvider = "Authentication")
    public void VerifyProductDetailsWithShoppingCart(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		 try
		   {
			// reterive the data from products details
			Amazon_Pg_Obj.viewProductDetailsPage();
			// reterive the data from Shopping cart
			Amazon_Pg_Obj.addToCart();
			// comparing the Product detail with Cart summary details
			if(Amazon_Pg_Obj.verifyDetailsAndCart() == true)
			{
		     img.capturesnapshot();
	       	 img.reportsnapshotPass(amz_Product.Verify_PROD_DETA_CART);
		    }
		   }
		 catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Fail : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAIL");
			   AssertJUnit.assertTrue(amz_Product.FIND_PROD, acnt.isDisplayed());
			   throw(e);
		   }
		
    }
	//This function will "search for an item and add to cart and purchase it"
	@Test(priority = 7, dataProvider = "Authentication")
    public void ReachCheckoutSummaryVerifProducts(String Mobile_Number, String Password, String Item_Name, String Card_Number) throws Exception
    { 
		   try
		   {  
			   cmn.Info("Search Item");
			    srch.ListingItem(Item_Name);

			    //This string will find the TV from the search list 
			     String name = amz_Product.PROD_SELECT;
			     
			   //This string will store the selected TV name   
			     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(2)).scrollIntoView(new UiSelector().textContains(\""+name+"\").instance(0))").click();
		 		
			   //This string will store the Price of the TV and save
			     String price = driver.findElement(By.xpath(amz_Product.FIND_PRIZE)).getText();
			     
			   //This string will store the Description the TV brand and details
			     @SuppressWarnings("unused")
				 String dscrptn = driver.findElement(By.xpath(amz_Product.FIND_DESCP)).getText();
			     
			   //This string will store the selected item to click on the ADD to cart a item
			     
			     driver.findElement(By.xpath(amz_Product.CART_ADD)).click();
			     Thread.sleep(1500);
			     
			        if(driver.getPageSource().contains("Added to cart"))
	  		    	{
	  		    		 img.capturesnapshot();
	  				     img.reportsnapshotPass("Item Added to Cart" );
	  		    	}
			        
			       // Action button Click on cart button
			         driver.findElement(By.xpath(amz_Product.CART_COUNT)).click();
			         
			      // Action button Click on proceed to buy button click
			           driver.findElement(By.xpath(amz_Product.PROCEE_BUY)).click();
			           
			        // Action button Click on Use the existing or current address
			           driver.findElement(By.xpath(amz_Product.CHECK_ADDRESS)).click();
			           
			        // Action button Click on Continue button to proceed the item 
			           driver.findElement(By.xpath(amz_Product.CONT_CARD)).click(); 
			           
			         // Action button Click on click on Credit/ Debit card details
			           driver.findElement(By.xpath(amz_Product.ADD_CARD)).click();
			           
			         //Verify Item name on cart page
			 		   String Itemname = amz_Product.PROD_SELECT;;
			 		   
			 		  // comparing the Product details Price and name in Cart summary details
			 		  
			  		    	if(name.equals(Itemname))
			  		    	{
			  		    		 img.capturesnapshot();
			  				     img.reportsnapshotPass(amazon_Msgs.ITEM_MATCH);
			  		    	}
			  		    	
			  		       //Verify the price on amazon checkout page
					 		   String pricess = driver.findElement(By.xpath(amz_Product.FIND_PRIZE)).getText();
					 		   
					  		      //Verify Item the price before click on purchase the item 
					  		    	if(pricess.equals(price))
					  		    	{
					  		    		 img.capturesnapshot();
					  				     img.reportsnapshotPass(amazon_Msgs.PRICE_MATCH);
					  		    	}
			           
			          //Get the credit card details from Card payment page object class and Reach till Order Submission page
			           card.paymentType(Card_Number);
 		   }  
		   catch(Exception e)
		   {
			   e.getMessage();
			   img.capturesnapshot_Fail();
			   img.reportsnapshotFail("Failed : " + e.fillInStackTrace());	
			   Assert.assertFalse(false, "FAILED");
			   AssertJUnit.assertTrue(amazon_Msgs.TECH_ERROR, acnt.isDisplayed());
			   throw(e);
		   }
    }

	//This function will authenticate and return the object from excel sheet
	@DataProvider
    public String[][] Authentication() throws Exception
    {   
		
 	     String POS_LoginExcel=properties.getProperty(amazon_Var.Excel_Path);
 	     String[][] testObjArray = LoginUtils.getTableArray(POS_LoginExcel+amazon_Var.TD_Path,amazon_Var.TD_PageName);
 	     return testObjArray;
 	     
    }
    
    @AfterClass
    public void closeApplication() throws InterruptedException
    {
              driver.quit();
    }
}
