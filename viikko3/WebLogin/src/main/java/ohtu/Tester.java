package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        String url = "http://localhost:4567";
        driver.get(url);
        
        sleep(2);

        // oikea käyttäjätunnus, väärä salasana
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pakke");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        sleep(2);

        // uuden käyttäjätunnuksen luominen
        driver.get(url);
        element = driver.findElement(By.linkText("register new "
                + "user"));
        element.click();
        sleep(2);

        Random r = new Random();
        driver.findElement(By.name("username"))
                .sendKeys("arto" + r.nextInt(100000));
        driver.findElement(By.name("password"))
                .sendKeys("passw0rd1");
        driver.findElement(By.name("passwordConfirmation"))
                .sendKeys("passw0rd1");
        driver.findElement(By.name("signup")).submit();
        sleep(2);

        // uuden tunnuksen luomisen jälkeinen uloskirjautuminen
        driver.findElement(By.linkText("continue to application mainpage"))
                .click();
        sleep(2);
        driver.findElement(By.linkText("logout")).click();

        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
