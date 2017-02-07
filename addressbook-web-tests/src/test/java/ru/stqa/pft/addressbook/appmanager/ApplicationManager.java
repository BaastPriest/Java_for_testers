package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 03.02.2017.
 */
public class ApplicationManager {

  public final GroupHelper groupHelper = new GroupHelper();

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    groupHelper.wd = new FirefoxDriver();
    groupHelper.wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    groupHelper.wd.get("http://localhost:8080/addressbook/group.php");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    groupHelper.wd.findElement(By.name("user")).click();
    groupHelper.wd.findElement(By.name("user")).clear();
    groupHelper.wd.findElement(By.name("user")).sendKeys(username);
    groupHelper.wd.findElement(By.name("pass")).click();
    groupHelper.wd.findElement(By.name("pass")).clear();
    groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
    groupHelper.wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void gotoGroupPage() {
    groupHelper.wd.findElement(By.linkText("groups")).click();
  }

  public void stop() {
    groupHelper.wd.quit();
  }

}
