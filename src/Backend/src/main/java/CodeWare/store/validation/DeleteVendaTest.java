package CodeWare.store.validation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteVendaTest {
    
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Acesse sua página hospedada (ajuste para http://localhost:... se for via Spring Boot)
            driver.get("http://localhost:8080/html/gerenciarVendas.html"); // ou file:///C:/...

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera até pelo menos uma linha aparecer
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#tabela-vendas tr")));

            // Localiza todas as linhas da tabela
            List<WebElement> linhas = driver.findElements(By.cssSelector("#tabela-vendas tr"));

            if (linhas.isEmpty()) {
                System.out.println("Nenhuma venda listada.");
                return;
            }

            // Pega a primeira linha
            WebElement primeiraLinha = linhas.get(0);

            // Pega o botão de excluir dentro da linha
            WebElement botaoExcluir = primeiraLinha.findElement(By.cssSelector(".btn-excluir"));

            // Força o clique no botão
            botaoExcluir.click();

            // Espera e aceita o alerta de confirmação (window.confirm)
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept(); // Confirma exclusão

            System.out.println("Botão de exclusão clicado e confirmado com sucesso!");

            // Espera atualização da tabela (pode ser feito com outra lógica mais precisa)
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
