package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com", "test1"));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
    /* Для закрытия диалогового окна (alert), которое появляется при удалении контакта, нужно использовать такую команду драйвера:
    wd.switchTo().alert().accept(); */


    }
}

