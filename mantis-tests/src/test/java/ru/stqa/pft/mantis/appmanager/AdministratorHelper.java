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
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void selectUser() {
        click(By.xpath("//a[contains(.,'Manage')]"));
        click(By.xpath("//a[contains(.,'Manage Users')]"));
        List<WebElement> users = wd.findElements(By.xpath("//*[@class=\"row-2\" or @class=\"row-1\"]//a"));
        WebElement selectedUser = users.stream().filter((u) -> !(u.getText().equals(app.getProperty("web.adminLogin"))))
                .findFirst().get();
        selectedUser.click();
    }

    public User getUser() {
        String name = wd.findElement(By.name("username")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        return new User().withUsername(name).withEmail(email);
    }

    public void resetPassword() {
        click(By.xpath("//input[@value='Reset Password']"));
    }
}

