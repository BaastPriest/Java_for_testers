package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test //Измениала название метода. Название метода и класса не должны совпадать. Метод с маленькой буквы
    public void testContactCreation() {
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().initContactCreation();
      ContactData contact = new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com", "test1");
      app.getContactHelper().createContact(contact);
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);

        // сравнение
      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2 ) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
    }
  }