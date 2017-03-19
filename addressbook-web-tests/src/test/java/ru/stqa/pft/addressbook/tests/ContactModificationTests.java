package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com", "test1"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = (before.size() - 1);
        ContactData contact = new ContactData(before.get(index).getId(), "Change_myname", "Change_mylastname", "Change_88007006050", "Change_alena@yandex.com", null);
        app.getContactHelper().modifyContact(index, contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(index); //удаление последнего элемента из списка
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2 ) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after); //преобразование списков в множенства
    }
}
