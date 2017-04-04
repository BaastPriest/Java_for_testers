package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis(); //возвращает текущее время в мс (миллисекунда(1/1000 доля секунды))
        String user = String.format("user$s", now);// %s
        String password = "password";
        String email = String.format("user1%s@localhost.colcaldomain", now); // %s
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m)-> m.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
       return regex.getText(mailMessage.text); //возвращает текст соответствующий регулярному выражению
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
