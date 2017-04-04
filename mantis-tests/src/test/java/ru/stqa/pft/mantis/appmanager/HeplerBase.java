package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HeplerBase {

    protected ApplicationManager app;
    protected WebDriver wd;

    public HeplerBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator); //клик по полю ввода
        if (text != null) { //не вводит дефолтные значения, и не вводит текст,ксли он уже совпадает
            String existionText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existionText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null) { //сокращенная форма конструктора if
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }
        }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
