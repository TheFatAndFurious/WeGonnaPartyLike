package base;

import config.Config;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailService {

    private Config config = new Config();

    public void sendSimpleEmail() throws EmailException {
        SimpleEmail simpleEmail = new SimpleEmail();

        simpleEmail.setHostName(config.getEmailHost());
        simpleEmail.setSmtpPort(config.getEmailPort());
        simpleEmail.setAuthenticator(new DefaultAuthenticator(config.getEmailUsername(), config.getEmailPassword()));
        simpleEmail.setSSLOnConnect(true);
//        simpleEmail.setStartTLSEnabled(true);
        simpleEmail.setFrom(config.getEmailUsername(), "Matt");
        simpleEmail.setSubject("Coucou");
        simpleEmail.setMsg("Test de mail");
        simpleEmail.addTo("mrguerrilla@gmail.com");
        simpleEmail.send();
    }
}
