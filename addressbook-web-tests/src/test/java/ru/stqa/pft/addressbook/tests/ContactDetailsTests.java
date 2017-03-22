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

public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size() ==0) {
            app.contact().create(new ContactData().withFirstname("myTestName").withLastname("myTestlastname")
                    .withEmail("alena@yandex.com").withAddress("New Zealand,Auckland, New World St. 15")
                    .withMobilePhone("88007006050"));
        }
    }
    @Test
    public void testContactDetails(){
        app.goTo().homePage();
    }
}
