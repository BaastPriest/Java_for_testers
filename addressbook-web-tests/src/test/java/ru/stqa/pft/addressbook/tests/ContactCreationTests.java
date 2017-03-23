package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test //(enabled = false)
    public void testContactCreation() {
      app.goTo().homePage();
      Contacts before = app.contact().all();
      app.contact().initContactCreation();
        File photo = new File("src/test/resources/cat.jpg");
        ContactData contact = new ContactData().withFirstname("myname").withLastname("mylastname").withMobilePhone("88007006050").withEmail("alena@yandex.com").withPhoto(photo);
      app.contact().create(contact);
      app.goTo().homePage();
      assertThat(app.contact().count(), equalTo(before.size() + 1));
      Contacts after = app.contact().all();
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testBadContactCreation()  {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.contact().initContactCreation();
        ContactData contact = new ContactData().withFirstname("'").withLastname("mylastname").withMobilePhone("88007006050").withEmail("alena@yandex.com").withGroup("test1");
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File("");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/cat.jpg");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.exists());
    }
  }