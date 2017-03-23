package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        type(name("mobile"), contactData.getMobilePhone());
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

    public void details(ContactData contact) {
        gotoHomePage();
        wd.findElement(By.xpath("//img[@title='Details']/..")).click();
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
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public Contacts allDetails () {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts ();
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.id("content"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(tagName("br"));
            String address = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(5).getText();
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            contactCache.add(new ContactData().withAddress(address).withFirstname(firstName).withLastname(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails));
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
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getText();
        String email2 = wd.findElement(By.name("email2")).getText();
        String email3 = wd.findElement(By.name("email3")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }


    public ContactData infoFromEditFormWithoutId(ContactData contact) {
        initContactModificationWithoutId();
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getText();
        String email2 = wd.findElement(By.name("email2")).getText();
        String email3 = wd.findElement(By.name("email3")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public ContactData infoFromHomeTableForm (ContactData contact) {
        details(contact);
        String firstname = wd.findElement(By.xpath("//tr[@name = 'entry']//td[3]")).getText();
        String lastname = wd.findElement(By.xpath("//tr[@name = 'entry']//td[2]")).getText();
        String mobile = wd.findElement(By.xpath("//tr[@name = 'entry']//td[6]")).getText();
        String address = wd.findElement(By.xpath("//tr[@name = 'entry']//td[4]")).getText();
       String Email = wd.findElement(By.xpath("//tr[@name = 'entry']//td[5]")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
                withAllPhones(mobile).withAddress(address).withAllEmails(Email);
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

    private void initContactModificationWithoutId() {
        wd.findElement(By.xpath("//td[@class='center']//img[@src='icons/pencil.png']//..")).click();
    }
}
