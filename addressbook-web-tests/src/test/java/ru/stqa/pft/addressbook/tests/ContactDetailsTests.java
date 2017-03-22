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
        ContactData contactDetails = app.contact().all().iterator().next();
        ContactData infoFromHomeTableForm = app.contact().infoFromHomeTableForm(contactDetails);
        assertThat(contactDetails.getFirstname(), equalTo(infoFromHomeTableForm.getFirstname()));
        assertThat(contactDetails.getLastname(), equalTo(infoFromHomeTableForm.getLastname()));
        assertThat(contactDetails.getAddress(), equalTo(infoFromHomeTableForm.getAddress()));
        assertThat(contactDetails.getAllPhones(), equalTo(infoFromHomeTableForm.getAllPhones()));
        assertThat(contactDetails.getAllEmails(), equalTo(infoFromHomeTableForm.getAllEmails()));
    }
}
