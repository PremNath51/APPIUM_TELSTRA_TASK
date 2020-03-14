package Implementation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// This class calls the load function of the list of items displayed in the amazon search list.
public class ListingItem 
{
	
	@FindBy(xpath="//*[contains(text(), 'Search')]")
	private WebElement Srch;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement btn;
	
	@FindBy(xpath="//*[contains(text(), 'TOSHIBA 43LF711U20 43-inch 4K Ultra HD Smart LED TV HDR - Fire TV Edition')]")
	private WebElement product;
	
	public void ListingItem(String Itm)
	{
		Srch.sendKeys(Itm);
		btn.click();
		
	}

}
