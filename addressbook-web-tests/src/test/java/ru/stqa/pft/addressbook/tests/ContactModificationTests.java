package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("myname").withLastname("mylastname").withMobile("88007006050").withEmail("alena@yandex.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Change_myname").withLastname("Change_mylastname").withMobile("Change_88007006050").withEmail("Change_alena@yandex.com");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(modifiedContact); //удаление последнего элемента из списка
        before.add(contact);
        Assert.assertEquals(before, after); //преобразование списков в множенства
    }
}
