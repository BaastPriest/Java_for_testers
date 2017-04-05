package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.User;

import java.util.List;

public class AdministratorHelper extends HeplerBase {


    public AdministratorHelper(ApplicationManager app) {
        super(app);
    }

    public void administratorLogin(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void selectUser() {
        click(By.id("password-current"));
        click(By.id("password"));
        List<WebElement> users = wd.findElements(By.id("password-confirm"));
        WebElement selectedUser = users.stream().filter((u) -> !(u.getText().equals(app.getProperty("web.adminLogin"))))
                .findFirst().get();
        selectedUser.click();
    }

    public void changePassword(String password, String newPassword) {
        click(By.id("password-current"));
        type(By.id("password-current"), password);
        click(By.id("password"));
        type(By.id("password"), newPassword);
        click(By.id("password-confirm"));
        type(By.id("password-confirm"), newPassword);

    }

    /*public User getUser() {
        String name = wd.findElement(By.name("username")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        return new User().withUsername(name).withEmail(email);
    }*/

    public void confirmChangingPassword() {
        click(By.xpath("//input[@value='Изменить учетную запись']"));
    }
}

