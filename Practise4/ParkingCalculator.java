package testNG;

import com.google.common.base.Verify;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParkingCalculator {

    static By locChooseALot = By.xpath("//tr[td[contains(text(), 'Choose a Lot')]]//select[@id='Lot']");

    static By locStartTime = By.xpath("//tr[td[contains(text(),'Choose Entry Date and Time')]]//input[@name = 'EntryTime']");
    static By locListEntryTime = By.xpath("//tr[td[contains(text(),'Choose Entry Date and Time')]]//input[@name = 'EntryTimeAMPM']");
    static By locStartDate = By.xpath("//tr[td[contains(text(),'Choose Entry Date and Time')]]//input[@name = 'EntryDate']");

    static By locExitTime = By.xpath("//tr[td[contains(text(),'Choose Leaving Date and Time')]]//input[@name = 'ExitTime']");
    static By locListExitTimeAMPM = By.xpath("//tr[td[contains(text(),'Choose Leaving Date and Time')]]//input[@name = 'ExitTimeAMPM']");
    static By locExitDate = By.xpath("//tr[td[contains(text(),'Choose Leaving Date and Time')]]//input[@name = 'ExitDate']");

    static By locSubmitButton = By.xpath("//input[@name='Submit' and @value='Calculate']");


    @Test
    @Parameters({"strChooseALotSelect","strStartTime", "strEntryTimeAMPM", "strStartDate",
                 "strExitTime", "strExitTimeAMPM", "strExitDate", "strCost", "strTime"})
    public void test(String strChooseALotSelect, String strStartTime, String strEntryTimeAMPM, String strStartDate,
                     String strExitTime, String strExitTimeAMPM, String strExitDate, String strCost, String strTime) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //////////////////////////////////////////////////////////////////////////////////////
        driver.get("http://adam.goucher.ca/parkcalc/index.php");

        Assert.assertEquals(driver.getTitle(), "Parking Calculator");//Checking correct name of site


        WebElement chooseALot = driver.findElement(locChooseALot);
        Select chooseALotselect = new Select(chooseALot);


        WebElement startTime = driver.findElement(locStartTime);
        List<WebElement> listEntryTimeAMPM = driver.findElements(locListEntryTime);
        WebElement startDate = driver.findElement(locStartDate);

        chooseALotselect.selectByValue(strChooseALotSelect);
        startTime.clear();
        startTime.sendKeys(strStartTime);
        selectRadioValue(listEntryTimeAMPM, strEntryTimeAMPM);
        startDate.clear();
        startDate.sendKeys(strStartDate);

        WebElement exitTime = driver.findElement(locExitTime);
        List<WebElement> listExitTimeAMPM = driver.findElements(locListExitTimeAMPM);
        WebElement exitDate = driver.findElement(locExitDate);

        exitTime.clear();
        exitTime.sendKeys(strExitTime);
        selectRadioValue(listExitTimeAMPM, strExitTimeAMPM);
        exitDate.clear();
        exitDate.sendKeys(strExitDate);

        WebElement submitButton = driver.findElement(locSubmitButton);
        submitButton.submit();

        Thread.sleep(1000);

        WebElement dollarCost = driver.findElement(By.xpath("//tr[contains(.,'COST')]/td//b[contains(.,'$')]"));
        WebElement calculateTime = driver.findElement(By.xpath("//tr[contains(.,'COST')]/td//b[contains(.,'Days')]"));

        System.out.println("Cost of Parking:");
        System.out.println(dollarCost.getText());
        System.out.println("Time in parking");
        System.out.println(calculateTime.getText());

        // Verify.verify(calculateTime.getText().contains(strTime));

        Assert.assertEquals(dollarCost.getText().trim(), strCost);
        Assert.assertEquals(calculateTime.getText().trim(), strTime);

        //Assert.assertTrue(calculateTime.getText().contains(strTime));

        driver.quit();

    }

    public void selectRadioValue(List<WebElement> list, String selectValue) {
        for(WebElement elem : list) {
            String paramValue = elem.getAttribute("value");
            if(StringUtils.equals(selectValue, paramValue)) {
                elem.click();
                return;
            }
        }
    }
}
