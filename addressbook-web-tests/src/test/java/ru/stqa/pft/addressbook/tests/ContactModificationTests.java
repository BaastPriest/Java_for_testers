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
        app.goTo().homePage();
        if(app.contact().List().size() == 0) {
            app.contact().create(new ContactData().withFirstname("myname").withLastname("mylastname").withMobile("88007006050").withEmail("alena@yandex.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().List();
        int index = (before.size() - 1);
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstname("Change_myname").withLastname("Change_mylastname").withMobile("Change_88007006050").withEmail("Change_alena@yandex.com");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().List();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(index); //удаление последнего элемента из списка
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2 ) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after); //преобразование списков в множенства
    }
}
