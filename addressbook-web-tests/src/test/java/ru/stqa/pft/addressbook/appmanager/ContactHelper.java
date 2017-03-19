package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

    public void initContactModification(int index) {
        wd.findElements(xpath("//table[@id='maintable']//["+index+"]//a/img"));  // Изменила путь, так как выдавало ошибку о недоступности пути. Сократила.
        click(xpath("//table[@id='maintable']//["+index+"]//a/img"));
    }

    public void deleteContact() {
        click(xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void submitContactModification() {
        click(name("update"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public int getContactCount() {
        return wd.findElements(name("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(tagName("td"));
            //String lastName = element.findElements(By.name("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();

            int id = Integer.parseInt (element.findElement(By.xpath("//td[@class = 'center']")).getAttribute("value"));
            //String id = element.findElement(By.tagName("tr")).getAttribute("value"); //поиск одного элемента внутри другого
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null);
        contacts.add(contact);
        }
        return contacts;
    }
}
