package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test //Измениала название метода. Название метода и класса не должны совпадать. Метод с маленькой буквы
    public void testContactCreation() {
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().initContactCreation();
      app.getContactHelper().createContact(new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com", "test1"));
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);
    }
  }