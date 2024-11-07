package base;

import config.Config;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


/**
 * This class will be used to send mails from the app.
 */


public class EmailService {

    private Config config = new Config();

    //TODO: rethink the params (maybe create a special data structure for this) also do we create a method specially for birthday reminders or make it more abstract to be able to use it for other type of mails
    public void sendSimpleEmail(String recipient, String subject, String message ) throws EmailException {
        SimpleEmail simpleEmail = new SimpleEmail();

        simpleEmail.setHostName(config.getEmailHost());
        simpleEmail.setSmtpPort(config.getEmailPort());
        simpleEmail.setAuthenticator(new DefaultAuthenticator(config.getEmailUsername(), config.getEmailPassword()));
        simpleEmail.setSSLOnConnect(true);
        simpleEmail.setFrom(config.getEmailUsername(), "Matt");
        simpleEmail.setSubject(subject);
        simpleEmail.setMsg(message);
        simpleEmail.addTo(recipient);
        simpleEmail.send();
    }
}
