import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    }


    //@AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    @DisplayName("Adding one product to cart should show product price in header")
    public void add_procuct_to_cart_should_header_show_product_price() {
        driver.get("http://localhost:8080/product/a-popular-" +
                "history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        WebElement  addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();
        WebElement price = driver.findElement(By.className("wc-block-mini-cart__amount"));
        Assertions.assertEquals("12,00 €", price.getText(),
                "Price is not correct");
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));


    }
    @Test
    public void loginTest() {
        driver.get("http://localhost:8080/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        String loginText = driver.findElement(By.className("woocommerce-MyAccount-content")).getText();

        Assertions.assertAll(
                () -> Assertions.assertNotEquals("http://localhost:8080/my-account/", driver.getCurrentUrl(),
                        "bad adress, it should be my account"),
                () -> Assertions.assertEquals("Hello admin (not admin? Log out)", loginText,
                        "bad login"),
                () -> Assertions.assertDoesNotThrow( () -> driver.findElement(By.className("woocommerce-aMyAccount-content")),
                        "The my account content is missing. User probably is not logged in")
        );


    }
}
