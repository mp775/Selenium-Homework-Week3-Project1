package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    public void selectMenu(String menu) {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='" + menu + "']"));
    }

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyPageNavigation() {
        selectMenu("Computers");
    }

    @After
    public void teardown() {
        closeBrowser();
    }


}
