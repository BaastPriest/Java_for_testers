package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

  public class ContactCreationTests extends TestBase {

    @Test //Измениала название метода. Название метода и класса не должны совпадать. Метод с маленькой буквы
    public void testContactCreation() {
      app.contactHelper.initContactCreation();
      app.contactHelper.fillContactForm(new ContactData("myname", "mylastname", "88007006050", "alena@yandex.com"));
      app.contactHelper.submitContactCreation();
      app.contactHelper.returnToContactPage();
    }

  }