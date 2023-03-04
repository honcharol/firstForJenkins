package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClickHelpUiTest {
    @Test
    public void clickOnHelp(){
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://gorest.co.in/");
        webDriver.manage().window().setSize(new Dimension(1900,1000));
        webDriver.findElement(By.xpath("//a[@class='nav-link'][contains(.,'Help')]")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='text-center mb-3'][contains(.,'Questions and Answers')]")).getText())
                .isEqualTo("Questions and Answers");
        webDriver.quit();
    }
}
