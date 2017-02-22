package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

  public class ContactCreationTests extends TestBase {

    @Test //Измениала название метода. Название метода и класса не должны совпадать. Метод с маленькой буквы
    public void testContactCreation() {
      app.getContactHelper().initContactCreation();
      int before = app.getContactHelper().getContactCount();
      app.getContactHelper().createContact(new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com", "test1"));
      int after = app.getContactHelper().getContactCount();
      Assert.assertEquals(after, before + 1);
    }
  }