package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;// преобразование в тип File после, потому что библиотека

    @Parameter(names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));}
        else if (format.equals("json")){
        saveAsJson(contacts, new File(file));
         } else {
        System.out.println("Unrecognized format "  + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try ( Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath()); //просмотреть текущую директорию(запуск тестов-директория модуля явл.раб., запуск прогр.-директория проекта явл.рабочей)
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getMobilePhone(), contact.getEmail()));
            //запись в файл
        }
        writer.close(); //закрытие файла(все содержимоей файла записывается на диск и файл закрывается)
    }

    private List<ContactData> generateContacts(int count) {
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
