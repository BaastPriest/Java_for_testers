package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.List;
import static org.openqa.selenium.By.*;

public class ContactHelper extends HeplerBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(name("firstname"), contactData.getFirstname());
        type(name("lastname"), contactData.getLastname());
        type(name("mobile"), contactData.getMobile());
        type(name("email"), contactData.getEmail());

        if (creation) {
            new org.openqa.selenium.support.ui.Select(wd.findElement(name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(name("new_group")));
        }
    }

    public void initContactCreation() {
        click(linkText("add new"));
    }


    public void deleteContact() {
        click(xpath("//*[@value='Delete']"));
    }

    public void delete(ContactData contact) {
        initContactModificationById(contact.getId());
        deleteContact();
        contactCache = null;
        gotoHomePage();
    }

    public void submitContactModification() {
        click(name("update"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        contactCache = null;
        submitContactCreation();
    }

    public void modify(ContactData contact) {
        gotoHomePage();
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public int count() {
        return wd.findElements(By.xpath("//tr[@name = 'entry']//input")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return new Contacts(contactCache);
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    } //Перенесла, так как в NavigationHelper из этой части его не видно

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("firstname")).getAttribute("value");
        String work = wd.findElement(By.name("firstname")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void initContactModificationById(int id) { //выбираю кнопку редактирования(через нее удаление), что бы избежать подтверждающего удаление диалоговое окно
        //1 путь. реализация метода последовательных приближений
        //wd.findElement(By.xpath("//tr[@name = 'entry']//input[id='" + id + "']")).click();
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id))); //поиск чекбокса
        WebElement row = checkbox.findElement(By.xpath("./../..")); //переход к родительскому элементу
        List<WebElement> cells = row.findElements(By.tagName("td")); //выбор ячейки с карандашиком
        cells.get(7).findElement(By.tagName("a")).click(); //клик на карандашик
        //2 путь.
        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();//в xpath номера начинаются с 1, а не с 0
        //wd.findElement(By.xpath(String.format("//tr[.//input[@value=%s']]/td[8]/a", id))).click(); //подзапросы - начинаются с точки. поиск по ограничениям
        //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();// поиск по идентификатору в нужной ячейке
         }
}
