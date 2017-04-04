package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    //File photo = new File("src/test/resources/cat.jpg");
        public Iterator<Object[]> validContactsFromJson() throws IOException {
            try (BufferedReader reader =  new BufferedReader(new FileReader("src/test/resources/contacts.json"))){
                String json = "";
                String line = reader.readLine();
                while (line != null) {
                    json += line;
                    line =  reader.readLine();
                }
                Gson gson = new Gson();
                List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
                return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
            }
        }

    @Test (dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
          app.goTo().homePage();
          Contacts before = app.db().contacts();
          app.contact().initContactCreation();
          app.contact().create(contact);
          app.goTo().homePage();
          assertThat(app.contact().count(), equalTo(before.size() + 1));
          Contacts after = app.db().contacts();
          assertThat(after, equalTo(
                  before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
      }

    @Test (enabled = false)
    public void testBadContactCreation()  {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        app.contact().initContactCreation();
        ContactData contact = new ContactData().withFirstname("'").withLastname("lastname").withMobilePhone("88007006050").withEmail("alena@yandex.com");
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
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