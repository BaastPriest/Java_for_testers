package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size() ==0) {
            app.contact().create(new ContactData().withFirstname("myTestName").withLastname("myTestlastname")
                    .withEmail("alena@yandex.com").withEmail2("CATS@CAT.ru").withEmail3("123456@rambler.ru"));
        }
    }

    @Test
    public void testContactEmail(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! (s == null || s.equals("")))
                .collect(Collectors.joining("\n"));
    }
}
