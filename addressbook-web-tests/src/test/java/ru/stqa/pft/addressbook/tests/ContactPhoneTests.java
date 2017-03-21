package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size() ==0) {
            app.contact().create(new ContactData().withFirstname("myname").withLastname("mylastname").withMobile("88007006050").withEmail("alena@yandex.com").withGroup("test1"));
        }
    }

    //МЕТОД ОБРАТНЫХ ПРОВЕРОК
    @Test //(enabled = false)
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
       /* assertThat(contact.getAllPhones(), equalTo(cleaned(contactInfoFromEditForm.getAllPhones())));
        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getGetHomePhone()))); проверка тел по отдельности*/
    }

    private <T> String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getGetHomePhone(),contact.getMobile(), contact.getWorkPhone())
               .stream().filter((s) -> ! s.equals(""))
               .map(ContactPhoneTests::cleaned)
               .collect(Collectors.joining("\n"));

             /* пример реализации в старой версии
             String result = "";
             if (contact.getGetHomePhone() != null) {
             result = result + contact.getGetHomePhone();
             } + в качестве разделителя перевод строки */
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", ""); //регулярные выражения (пробельный символ)
    }
}
