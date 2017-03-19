package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() {
        app.goTo().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com", "test1"));
        }
        app.goTo().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size());
        app.getContactHelper().deleteContact();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();


        Assert.assertEquals(after.size(), before.size() - 1);
    /* Для закрытия диалогового окна (alert), которое появляется при удалении контакта, нужно использовать такую команду драйвера:
    wd.switchTo().alert().accept(); */

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
    }
}

