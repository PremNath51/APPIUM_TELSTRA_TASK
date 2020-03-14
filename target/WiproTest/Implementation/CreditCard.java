package Implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreditCard 
{
	
	@FindBy(xpath="//*[contains(text(), 'Card number')]")
	private WebElement numbr;
	
	@FindBy(xpath="//*[contains(text(), 'Expiry MM')]")
	private WebElement exdate;
	
	@FindBy(xpath="//*[contains(text(), 'Expiry YYYY')]")
	private WebElement exyear;
	
	@FindBy(xpath="//*[contains(text(), 'Add your card')]")
	private WebElement btn;
	
	
	public void paymentType(String nmbrs)
	{
		numbr.sendKeys(nmbrs);
		Select select = new Select(exdate.findElement(By.xpath("//*[contains(text(), 'Expiry MM')]")));
		select.deselectByIndex(8);
		Select select1 = new Select(exdate.findElement(By.xpath("//*[contains(text(), 'Expiry YYYY')]")));
		select1.deselectByIndex(4);
		btn.click();
		
	}

}
