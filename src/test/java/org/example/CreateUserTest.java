package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTest {

    @Test
    public void CreateUser(){
        String name = RandomStringUtils.random(5, true, false);
        String name2 = RandomStringUtils.random(7, true, false);
        String eMail = RandomStringUtils.random(6, true, true);
        String body = " {" +
                "\"name\":\""+ name+" "+name2 +"\", " +
                "\"gender\":\"male\", " +
                "\"email\":\"" + eMail + "@333oe.com\", " +
                "\"status\":\"active\"" +
                "}";
        RestAssured.baseURI = "https://gorest.co.in/";
        Response response = given().auth()
                .oauth2("49278559ddb988e1ce8a63b9f905015f7d958e6438d4b7010bc969d27b0d7b10")
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post("public/v2/users")
                .then()
                .statusCode(201)
                .extract()
                .response();
    }
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
