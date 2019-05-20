package testNG;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChromeTest {
    @Test
    public void LaunchChrome_Method1() {
        System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
        element.sendKeys("gta");
        element.submit();

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("GTA sites with url");
        Row row =sheet.createRow(0);
        row.createCell(0).setCellValue("URL Address");
        row.createCell(1).setCellValue("Site name");
        row.createCell(2).setCellValue("Number of times GTA met");

        for(int j = 0; j < 2; j++){
            List<WebElement> links = driver.findElements(By.xpath("//div[@class='r']//a[contains(.,'gta') or contains(.,'heft') or contains(.,'гта')]"));
            List<String> nameLinks = new ArrayList<>();
            for(WebElement el: links) {
                nameLinks.add(el.getText());
//                System.out.println(el.getAttribute("href"));
            }

            for(int i=0; i< nameLinks.size(); i++) {
                WebElement el = driver.findElement(By.linkText(nameLinks.get(i)));
                el.click();

                //el = driver.findElement(By.xpath("//html"));
                Row row2 = sheet.createRow(i + 1);
                row2.createCell(0).setCellValue(driver.getCurrentUrl());
                row2.createCell(1).setCellValue(driver.getTitle());
                //row2.createCell(2).setCellValue(StringUtils.countMatches(el.getText().toLowerCase(), "gta"));
                row2.createCell(2).setCellValue(StringUtils.countMatches(driver.getPageSource().toLowerCase(), "gta"));


//                System.out.println("Page url " + driver.getCurrentUrl());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                driver.navigate().back();
            }

            element = driver.findElement(By.xpath("//a[@id='pnnext']"));
            element.click();
        }

        try {
            OutputStream fileOut = new FileOutputStream("GtaSites.xls");
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
