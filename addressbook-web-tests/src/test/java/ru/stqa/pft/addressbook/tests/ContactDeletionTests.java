package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().List().size() ==0) {
            app.contact().create(new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com", "test1"));
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().List();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().List();
        Assert.assertEquals(after.size(), before.size() - 1);
    /* Для закрытия диалогового окна (alert), которое появляется при удалении контакта, нужно использовать такую команду драйвера:
    wd.switchTo().alert().accept(); */

    before.remove(index);
    Assert.assertEquals(before, after);
    }
}

