package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      app.goTo().homePage();
      Set<ContactData> before = app.contact().all();
      app.contact().initContactCreation();
      ContactData contact = new ContactData().withFirstname("myname").withLastname("mylastname").withMobile("88007006050").withEmail("alena@yandex.com").withGroup("test1");
      app.contact().create(contact);
      app.goTo().homePage();
      Set<ContactData> after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() + 1);

      contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
      before.add(contact);
      Assert.assertEquals(before, after);
    }
  }