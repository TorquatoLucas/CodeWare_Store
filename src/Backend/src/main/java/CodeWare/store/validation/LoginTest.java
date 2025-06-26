package CodeWare.store.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    public static void main(String[] args) {
        // Configura automaticamente o ChromeDriver
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        try {
            // Altere o caminho abaixo para a URL ou arquivo local correto
            driver.get("http://localhost:8080/");

            WebElement login = driver.findElement(By.id("login"));
            WebElement senha = driver.findElement(By.id("senha"));
            WebElement botaoLogar = driver.findElement(By.id("btnLogar"));

            login.sendKeys("lucas");
            senha.sendKeys("lucas");

            botaoLogar.click();

            Thread.sleep(3000); // opcional para visualização

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
