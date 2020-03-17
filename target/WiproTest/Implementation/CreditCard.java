package Implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import GlobalVariables.Amazon_Card;

public class CreditCard 
{
	
	@FindBy(xpath=Amazon_Card.CARD_NUMBER)
	private WebElement numbr;
	
	@FindBy(xpath=Amazon_Card.EX_DATE)
	private WebElement exdate;
	
	@FindBy(xpath=Amazon_Card.EX_YEAR)
	private WebElement exyear;
	
	@FindBy(xpath=Amazon_Card.ADD_BTN)
	private WebElement btn;
	
	
	public void paymentType(String nmbrs)
	{
		numbr.sendKeys(nmbrs);
		Select select = new Select(exdate.findElement(By.xpath(Amazon_Card.EX_DATE)));
		select.deselectByIndex(8);
		Select select1 = new Select(exdate.findElement(By.xpath(Amazon_Card.EX_YEAR)));
		select1.deselectByIndex(4);
		btn.click();
		
	}

}
