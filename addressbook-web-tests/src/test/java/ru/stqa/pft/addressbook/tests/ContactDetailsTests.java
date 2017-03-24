package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToDetailsPage();
        if (app.contact().allDetails().size() == 0) {
            app.contact().create(new ContactData().withFirstname("myTestName").withLastname("myTestlastname")
                    .withEmail("alena@yandex.com").withEmail2("ASFDSJDJN@gdfg.dg").withEmail3("12312@sdf.ru")
                    .withHomePhone("849580324").withWorkPhone("8800456512").withMobilePhone("88007006050")
                    .withAddress("New Zealand,Auckland, New World St. 15"));
        }
    }

    @Test
    public void testContactDetails() {
        ContactData  contactDetails = app.contact().allDetails().iterator().next();
        ContactData infoFromEditFormWithoutId = app.contact().infoFromEditFormWithoutId(contactDetails);

        assertThat(contactDetails.getAllDetails(), equalTo(mergeDetails(infoFromEditFormWithoutId)));
    }
        private String  mergeDetails(ContactData contact) {
            return Arrays.asList(contact.getFirstname() + " " + contact.getLastname(), contact.getAddress(),
                   "\nH: " + contact.getHomePhone(),"M: " + contact.getMobilePhone(),"W: " + contact.getWorkPhone(),
                   "\n" + contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                    .stream().filter((s)->!(s == null || s.equals("")))
                    .collect(Collectors.joining("\n"));
    }

}



