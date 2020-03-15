package Implementation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerSignin 
{

	// Finding WebElement id in the application screen
	
	@FindBy(xpath="//*[contains(text(), 'Already a customer? Sign In')]")
	private WebElement createNewAccount;
	
	@FindBy(xpath="//*[contains(text(), 'Mobile number or Email')]")
	private WebElement mobileNumber;
	
	@FindBy(xpath="//*[contains(text(), 'Continue')]")
	private WebElement continueFlow;
	
	@FindBy(xpath="//*[contains(text(), 'Amazon password')]")
	private WebElement accountPassword;
	
	@FindBy(xpath="//*[contains(text(), 'Login')]")
	private WebElement loginClick;
	
	
	// Functions for actions is added 
	
	public void CustomerSignin(String Mbl, String paswrd)
	{
		createNewAccount.click();
		mobileNumber.sendKeys(Mbl);
		continueFlow.click();
		accountPassword.sendKeys(paswrd);
		loginClick.click();
	}
	
	
	
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
