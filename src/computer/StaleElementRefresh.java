package computer;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static browserfactory.BaseTest.driver;

public class StaleElementRefresh {

    List<String> beforeFilterProductNameList;
    List<String> afterFilterProductNameList;

    public StaleElementRefresh(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void beforeFilter(){
        List<WebElement> beforeFilterProductName = driver.findElements(By.xpath("//h2[@class='product-title']"));
        List<String> beforeFilterProductNameList = new ArrayList<>();

        for(WebElement products: beforeFilterProductName){
            beforeFilterProductNameList.add(String.valueOf(products.getText()));
        }
        System.out.println("Product List before filter apply : " +beforeFilterProductNameList);
        Collections.reverse(beforeFilterProductNameList);
        System.out.println("Sort Array List in reverse Alphabetic Order : " +beforeFilterProductNameList);
    }

    public void afterFilter(){
        List<WebElement> afterFilterProductName = driver.findElements(By.xpath("//h2[@class='product-title']"));
        List<String> afterFilterProductNameList = new ArrayList<>();

        for(WebElement products: afterFilterProductName){
            afterFilterProductNameList.add(String.valueOf(products.getText()));
        }
        System.out.println("Product List after applying filter : " +afterFilterProductNameList);
    }

    public void compareArrayList(){
        Assert.assertEquals(beforeFilterProductNameList, afterFilterProductNameList);
    }
}
