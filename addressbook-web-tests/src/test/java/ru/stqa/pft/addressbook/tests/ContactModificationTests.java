package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com", "test1"));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Change_myname", "Change_mylastname", "Change_88007006050", "Change_alena@yandex.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before );
    }
}
