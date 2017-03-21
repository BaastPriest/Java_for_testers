package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size() ==0) {
            app.contact().create(new ContactData().withFirstname("myname").withLastname("mylastname").withMobile("88007006050").withEmail("alena@yandex.com").withGroup("test1"));
        }
    } //надо добавить еще два номера

    @Test //(enabled = false)
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
