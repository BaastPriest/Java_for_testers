package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/cat.jpg");
        list.add(new Object[] {new ContactData().withFirstname("firstname1").withLastname("lastname1").withMobilePhone("mobile1").withEmail("email1").withPhoto(photo)});
        list.add(new Object[] {new ContactData().withFirstname("firstname2").withLastname("lastname2").withMobilePhone("mobile2").withEmail("email2").withPhoto(photo)});
        list.add(new Object[] {new ContactData().withFirstname("firstname3").withLastname("lastname3").withMobilePhone("mobile13").withEmail("email3").withPhoto(photo)});
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
          app.goTo().homePage();
          Contacts before = app.contact().all();
          app.contact().initContactCreation();
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
        ContactData contact = new ContactData().withFirstname("'").withLastname("lastname").withMobilePhone("88007006050").withEmail("alena@yandex.com").withGroup("test1");
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

    @Test //(enabled = false)
    public void testCurrentDir(){
        File currentDir = new File("");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/cat.jpg");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.exists());// тут можно проверить права доступа,создать его,удалить и т.д.
    }
  }