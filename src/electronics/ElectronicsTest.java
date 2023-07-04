package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully(){
        WebElement electronicsTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        WebElement cellPhonesTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronicsTab).moveToElement(cellPhonesTab).click().perform();
        String actualCategories = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        System.out.println(actualCategories);
        Assert.assertEquals("Cell phones title not displayed correctly", "Cell phones", actualCategories);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully(){
        WebElement electronicsTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        WebElement cellPhonesTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronicsTab).moveToElement(cellPhonesTab).click().perform();
        String actualCategories = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        System.out.println(actualCategories);
        Assert.assertEquals("Cell phones title not displayed correctly", "Cell phones", actualCategories);

        clickOnElement(By.cssSelector("a[title='List']"));
        clickOnElement(By.xpath("//a[normalize-space()='Nokia Lumia 1020']"));

        String expectedProductName = "Nokia Lumia 1020";
        String actualProductName = getTextFromElement(By.xpath("//h1[normalize-space()='Nokia Lumia 1020']"));
        System.out.println(actualProductName);
        Assert.assertEquals("Product name displayed incorrectly", expectedProductName, actualProductName);

        String actualProductPrice = getTextFromElement(By.cssSelector("#price-value-20"));
        System.out.println(actualProductPrice);
        Assert.assertEquals("Product price displayed incorrectly", "$349.00", actualProductPrice);

        WebElement qtyTextBox = driver.findElement(By.cssSelector("#product_enteredQuantity_20"));
        qtyTextBox.clear();
        sendTextToElement((By) qtyTextBox,"2");
        clickOnElement(By.cssSelector("#add-to-cart-button-20"));
        String expectedHeaderMsg = "The product has been added to your shopping cart";
        String actualHeaderMsg = getTextFromElement(By.cssSelector(".content"));
        System.out.println(actualHeaderMsg);
        Assert.assertTrue("Product added message not displayed correctly", actualHeaderMsg.contains(expectedHeaderMsg));
        clickOnElement(By.cssSelector("span[title='Close']"));

        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        WebElement goToCart = driver.findElement(By.xpath("//button[normalize-space()='Go to cart']"));
        actions.moveToElement(shoppingCart).moveToElement(goToCart).click().perform();

        String expectedCartTitle = "Shopping cart";
        String actualCartTitle = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        System.out.println(actualCartTitle);
        Assert.assertEquals("Cart title is not displayed correctly", expectedCartTitle, actualCartTitle);

        String actualQty = getTextFromElement(By.xpath("//input[@id='itemquantity11240']"));
        System.out.println(actualQty);
        Assert.assertEquals("Quantity displayed incorrectly", "2", actualQty);

        String actualTotal = getTextFromElement(By.cssSelector(".product-subtotal"));
        System.out.println(actualTotal);
        Assert.assertEquals("Total displayed incorrectly", "$698.00", actualTotal);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

        String expectedWelcomeMsg = "Welcome, Please Sign In!";
        String actualWelcomeMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        System.out.println(actualWelcomeMsg);
        Assert.assertEquals("Welcome Sign In message not displayed correctly", expectedWelcomeMsg, actualWelcomeMsg);

        clickOnElement(By.cssSelector(".button-1.register-button"));
        String actualRegisterText = getTextFromElement(By.cssSelector("div[class='page-title'] h1"));
        Assert.assertEquals("Register text is not displayed", "Register", actualRegisterText);

        sendTextToElement(By.cssSelector("#FirstName"),"Geeta");
        sendTextToElement(By.cssSelector("#LastName"), "Joshi");
        sendTextToElement(By.cssSelector("#Email"),"geeta.joshi@gmail.com");
        sendTextToElement(By.cssSelector("#Password"),"Test123");
        sendTextToElement(By.cssSelector("#ConfirmPassword"),"Test123");
        clickOnElement(By.cssSelector("#register-button"));

        String expectedRegCompletedMsg = "Your registration completed";
        String actualRegCompletedMsg = getTextFromElement(By.cssSelector(".result"));
        System.out.println(actualRegCompletedMsg);
        Assert.assertEquals("Register Completed message is not displayed", expectedRegCompletedMsg, actualRegCompletedMsg);
        clickOnElement(By.cssSelector(".button-1.register-continue-button"));

        String actualCartText = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        System.out.println(actualCartTitle);
        Assert.assertEquals("Cart title is not displayed correctly", expectedCartTitle, actualCartText);

        clickOnElement(By.xpath("//a[normalize-space()='Log in']"));
        sendTextToElement(By.cssSelector("#Email"),"geeta.joshi@gmail.com");
        sendTextToElement(By.cssSelector("#Password"),"Test123");
        clickOnElement(By.cssSelector("button[class='button-1 login-button']"));


        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

//        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"),"Hetal");
//        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"),"Patel");
//        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "hetal.patel@gmail.com");
//        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
//        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
//        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "101 Repton Street");
//        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "BR5 1AB");
//        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07122234554");
//        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
//        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
//        clickOnElement(By.cssSelector("#shippingoption_2"));
//        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
//        clickOnElement(By.cssSelector("#paymentmethod_1"));
//        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
//        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"),"Visa");
//        sendTextToElement(By.cssSelector("#CardholderName"),"Hetal Patel");
//        sendTextToElement(By.cssSelector("#CardNumber"),"4111111111111111");
//        selectByVisibleTextFromDropDown(By.cssSelector("#ExpireMonth"),"07");
//        selectByVisibleTextFromDropDown(By.cssSelector("#ExpireYear"),"2025");
//        sendTextToElement(By.cssSelector("#CardCode"),"491");
//        clickOnElement(By.cssSelector(".button-1.payment-info-next-step-button"));
    }

    @After
    public void teardown() {
        closeBrowser();
    }

}
