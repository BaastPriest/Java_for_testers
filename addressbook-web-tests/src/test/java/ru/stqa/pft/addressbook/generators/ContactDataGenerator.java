package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);

    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath()); //просмотреть текущую директорию(запуск тестов-директория модуля явл.раб., запуск прогр.-директория проекта явл.рабочей)
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getMobilePhone(), contact.getEmail()));
            //запись в файл
        }
        writer.close(); //закрытие файла(все содержимоей файла записывается на диск и файл закрывается)
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("firstname %s", i))
            .withLastname(String.format("lastname-%s", i))
            .withMobilePhone(String.format("+7987654321%s", i)) //подумать над форматом
            .withEmail(String.format("Email_%s"+"@"+"mail.com", i))); //подумать над форматом
        }
        return contacts;
    }
}
