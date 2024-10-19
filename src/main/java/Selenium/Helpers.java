package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Helpers {
    static WebDriver driver;
    static void takeScreenshot() {
        driver = new ChromeDriver();
        System.out.println("Robię zrzut ekranu");

    }
}
