package Implementation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import GlobalVariables.Amazon_Cus_Sign;

public class CustomerSignin 
{

	// Finding WebElement id in the application screen
	
	@FindBy(xpath=Amazon_Cus_Sign.CREATE_NEW_ACC)
	private WebElement createNewAccount;
	
	@FindBy(xpath=Amazon_Cus_Sign.MOB_EMAIL)
	private WebElement mobileNumber;
	
	@FindBy(xpath=Amazon_Cus_Sign.CONT_FLOW)
	private WebElement continueFlow;
	
	@FindBy(xpath=Amazon_Cus_Sign.ACC_PASS)
	private WebElement accountPassword;
	
	@FindBy(xpath=Amazon_Cus_Sign.LOGIN)
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
