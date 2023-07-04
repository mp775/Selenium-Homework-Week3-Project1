package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.List;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        clickOnElement(By.xpath("(//a[normalize-space()='Computers'])[1]"));
        clickOnElement(By.cssSelector("h2[class='title'] a[title='Show products in category Desktops']"));
        StaleElementRefresh s = new StaleElementRefresh(driver);
        s.beforeFilter();

        WebElement dropDown = driver.findElement(By.id("products-orderby"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name: Z to A");
        driver.navigate().refresh();
        s.afterFilter();
        s.compareArrayList();
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        clickOnElement(By.xpath("(//a[normalize-space()='Computers'])[1]"));
        clickOnElement(By.cssSelector("h2[class='title'] a[title='Show products in category Desktops']"));
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");
        driver.navigate().refresh();
        List<WebElement> addToCartBtn = driver.findElements(By.xpath("//div[@class='buttons']/button"));
        addToCartBtn.get(0).click();
        String expectedProductName = "Build your own computer";
        String actualProductName = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        System.out.println(actualProductName);
        Assert.assertEquals("Product Name displayed incorrectly", expectedProductName, actualProductName);

        selectByVisibleTextFromDropDown(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByVisibleTextFromDropDown(By.id("product_attribute_2"), "8GB [+$60.00]");
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        //clickOnElement(By.xpath("//input[@id='product_attribute_5_10']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        Thread.sleep(500);
        String expectedPrice = "$1,475.00";
        String actualPrice = getTextFromElement(By.cssSelector("#price-value-1"));
        System.out.println(actualPrice);
        Assert.assertEquals("Product Price not displayed correctly", expectedPrice, actualPrice);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        String expectedNotification = "The product has been added to your shopping cart";
        String actualNotification = getTextFromElement(By.xpath("//p[@class='content']"));
        System.out.println(actualNotification);
        Assert.assertEquals("Notification displayed incorrectly", expectedNotification, actualNotification);
        Thread.sleep(300);
        clickOnElement(By.xpath("//span[@title='Close']"));
        Thread.sleep(200);
        Actions actions = new Actions(driver);
        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        actions.moveToElement(shoppingCart).click().perform();
        WebElement goToCart = driver.findElement(By.cssSelector(".button-1.cart-button"));
        scrollDown(driver, goToCart);
        goToCart.click();
        //actions.moveToElement(goToCart).click().perform();

        String expectedCartTitle = "Shopping cart";
        String actualCartTitle = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        System.out.println(actualCartTitle);
        Assert.assertEquals("Cart title is not displayed correctly", expectedCartTitle, actualCartTitle);

        WebElement qtyBox = driver.findElement(By.xpath("//td[@class='quantity']/input"));
        qtyBox.clear();
        qtyBox.sendKeys("2");
        clickOnElement(By.xpath("//button[@id='updatecart']"));

        String expectedTotal = "$2,950.00";
        String actualTotal = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        System.out.println(actualTotal);
        Assert.assertEquals("Total price displayed incorrectly", expectedTotal, actualTotal);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

        String expectedWelcomeMsg = "Welcome, Please Sign In!";
        String actualWelcomeMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        System.out.println(actualWelcomeMsg);
        Assert.assertEquals("Welcome Sign In message not displayed correctly", expectedWelcomeMsg, actualWelcomeMsg);
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Ketan");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Faldu");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "ketan.faldu@gmail.com");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "101 Repton Street");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "BR5 1AB");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07122234554");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Visa");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Hetal Patel");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5555555555554444");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "05");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2025");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "912");
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        String expectedPaymentMethod = "Credit Card";
        String actualPaymentMethod = getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        System.out.println(actualPaymentMethod);
        Assert.assertEquals("Payment method is not displayed correctly", expectedPaymentMethod, actualPaymentMethod);

        String expectedShippingMethod = "Next Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        System.out.println(actualShippingMethod);
        Assert.assertEquals("Shipping method is not displayed correctly", expectedShippingMethod, actualShippingMethod);

        String expectedTotalPrice = "$2,950.00";
        String actualTotalPrice = getTextFromElement(By.cssSelector("span[class='value-summary'] strong"));
        System.out.println(actualTotalPrice);
        Assert.assertEquals("The total price displayed incorrectly", expectedTotalPrice, actualTotalPrice);

        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));
        String actualGreetingMsg = getTextFromElement(By.cssSelector("div[class='page-title'] h1"));
        System.out.println(actualGreetingMsg);
        Assert.assertEquals("Thank you message is not displayed", "Thank you", actualGreetingMsg);

        String expectedOrderProcessedMsg = "Your order has been successfully processed!";
        String actualOrderProcessedMsg = getTextFromElement(By.cssSelector("div[class='section order-completed'] div[class='title'] strong"));
        System.out.println(actualOrderProcessedMsg);
        Assert.assertEquals("Order Proceed message is not displayed", expectedOrderProcessedMsg, actualOrderProcessedMsg);
        clickOnElement(By.cssSelector(".button-1.order-completed-continue-button"));

        String actualWelcomeStoreMsg = getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']"));
        System.out.println(actualWelcomeStoreMsg);
        Assert.assertEquals("Welcome to store message is not displayed", "Welcome to our store", actualWelcomeStoreMsg);
    }

    @After
    public void teardown() {
        //closeBrowser();
    }
}
