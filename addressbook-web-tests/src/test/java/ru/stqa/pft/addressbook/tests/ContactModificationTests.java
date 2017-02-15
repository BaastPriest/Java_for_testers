package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().returnToContactPage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Change_myname", "Change_mylastname", "Change_88007006050", "Change_alena@yandex.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
  }
}
