package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0) {
        app.goTo().homePage();
        app.contact().create(new ContactData().withFirstname("myname").withLastname("mylastname").withMobilePhone("88007006050").withEmail("alena@yandex.com"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().getActualContacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Change_myname").withLastname("Change_mylastname").withMobilePhone("123456789").withEmail("Change_alena@yandex.com");
        app.contact().modifyWithoutId(contact); //!
        Contacts after = app.db().getActualContacts();
        assertEquals(after.size(), before.size());
        ContactData beforeContact = before.without(modifiedContact).withAdded(contact).next();
        ContactData afterContact = after.next();

        assertThat(afterContact.getFirstname(),
                equalTo(beforeContact.getFirstname()));
        assertThat(afterContact.getLastname(),
                equalTo(beforeContact.getLastname()));
        assertThat(afterContact.getMobilePhone(),
                equalTo(beforeContact.getMobilePhone()));
    }
}
