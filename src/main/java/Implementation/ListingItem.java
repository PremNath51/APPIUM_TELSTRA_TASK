package Implementation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import GlobalVariables.Amazon_Cus_Sign;

// This class calls the load function of the list of items displayed in the amazon search list.
public class ListingItem 
{
	
	@FindBy(xpath=Amazon_Cus_Sign.SEARCH)
	private WebElement Srch;
	
	@FindBy(xpath=Amazon_Cus_Sign.SUBMIT)
	private WebElement btn;
	
	@FindBy(xpath=Amazon_Cus_Sign.PRODUCT)
	private WebElement product;
	
	public String ListingItem(String Itm)
	{
		Srch.sendKeys(Itm);
		btn.click();
		return Itm;
		
	}

}
