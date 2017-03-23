package ru.stqa.pft.addressbook.tests;
/*Тест должен проверять только один какой-нибудь контакт,
сравнивать информацию о контакте,
которая представлена на тестируемой странице,
с информацией, представленной в форме редактирования контакта.
метод обратных проверок
Контакт, у которого заполнена только те поля,
которые отображаются на главной странице
 -- имя, фамилия, адреса и телефоны.
 Остальные поля оставьте пустыми.
 И не включайте этот контакт ни в какие группы.*/

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
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("myTestName").withLastname("myTestlastname")
                    .withEmail("alena@yandex.com").withEmail2("ASFDSJDJN@gdfg.dg").withEmail3("12312@sdf.ru")
                    .withHomePhone("849580324").withWorkPhone("8800456512").withMobilePhone("88007006050")
                    .withAddress("New Zealand,Auckland, New World St. 15"));
        }
    }

    @Test
    public void testContactDetails() {
        ContactData contactDetails = app.contact().allDetails().iterator().next();
        app.contact().details(contactDetails);
        ContactData infoFromEditFormWithoutId = app.contact().infoFromEditFormWithoutId(contactDetails);
        assertThat(contactDetails.getAllPhones(), equalTo(mergePhones(infoFromEditFormWithoutId)));
        assertThat(contactDetails.getAllNames(), equalTo(mergeNames(infoFromEditFormWithoutId)));
        assertThat(contactDetails.getAddress(), equalTo(infoFromEditFormWithoutId));
        assertThat(contactDetails.getAllEmails(), equalTo(mergeEmails(infoFromEditFormWithoutId)));

    }
    private String mergeNames(ContactData contact) {
        return Arrays.asList(contact.getFirstname(), contact.getLastname())
                .stream().filter((s)->!(s == null || s.equals("")))
                .collect(Collectors.joining(""));
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getMobilePhone(), contact.getWorkPhone(), contact.getHomePhone())
               .stream().filter((s) -> !(s == null || s.equals("")))
               .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s)->!(s == null || s.equals("")))
                .collect(Collectors.joining("\n"));
    }
}
