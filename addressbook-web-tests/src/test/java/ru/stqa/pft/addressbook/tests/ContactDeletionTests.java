package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getContactHelper().returnToContactPage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().deleteContact();
    /* Для закрытия диалогового окна (alert), которое появляется при удалении контакта, нужно использовать такую команду драйвера:
    wd.switchTo().alert().accept(); */
  }
}

