package PageObjects;

import GlobalVariables.amz_Product;
import Implementation.AmazonTest;
import Implementation.CreditCard;
import Implementation.CustomerSignin;
import Implementation.ListingItem;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.velocity.test.provider.BoolObj;
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
import PageObjects.Amazon_Pg_Obj;
import Utility.CommonFunctions;

public class Amazon_Pg_Obj {
	 CommonFunctions cmn;
	 ListingItem srch;

	 // This function will get the Product Sizes 50-65 inch Tv Products
	    public static String searchProductSizeItems()
	    {
	    	CommonFunctions cmn = new CommonFunctions();
	    	ListingItem srch = new ListingItem();
	    	 cmn.Info("Search Item");
			return srch.ListingItem(amz_Product.FIND_SIZE);
	    }
	    
	 // This function will get the Selected Tv Products
	    public static String selectProductSearchItem()
	    {
	    	CommonFunctions cmn = new CommonFunctions();
	    	ListingItem srch = new ListingItem();
	    	 cmn.Info("Search Item");
			return srch.ListingItem(amz_Product.PROD_SELECT);
	    }
	    
	 // This function will give the Selected Products
	    public static String viewProductDetailsPage()
	    {
	    	CommonFunctions cmn = new CommonFunctions();
	    	ListingItem srch = new ListingItem();
	    	cmn.Info("Search Item");
			srch.ListingItem(amz_Product.PROD_SELECT);
	    	  //This string will store the Price of the TV and save
		     String price = AmazonTest.driver.findElement(By.xpath(amz_Product.FIND_PRIZE)).getText();
		     
		   //This string will store the Description the TV brand and details
			 String dscrptn = AmazonTest.driver.findElement(By.xpath(amz_Product.FIND_DESCP)).getText();
		     String PriceDescrition = price + dscrptn;
		     return PriceDescrition;
	    }
	    
	    public static boolean addToCart()
	    {
	    	AmazonTest.driver.findElement(By.xpath("//*[contains(text(), 'Add to Cart')]")).click();
	    	return true;
	    }
	    // Verify the details from cart
	    public static boolean verifyDetailsAndCart() throws InterruptedException
	    {
	    	boolean success;
	        if(AmazonTest.driver.getPageSource().contains("Added to cart"))
	        {
	        	success = true;
	 	       // Action button Click on cart button
	    	    AmazonTest.driver.findElement(By.xpath(amz_Product.CART_COUNT)).click();
	    	    Thread.sleep(1500);
		    }

	    return true;
	    }
	    

}
