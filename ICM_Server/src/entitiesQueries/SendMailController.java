package entitiesQueries;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// TODO: Auto-generated Javadoc
/**
 * This class handles the e-mail sending.
 *
 * @author adial
 */
public class SendMailController 
{
	
	/**
	 * Send plain text email.
	 *
	 * @param host the host
	 * @param port the port
	 * @param userName the user name
	 * @param password the password
	 * @param toAddress the to address
	 * @param subject the subject
	 * @param message the message
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public void sendPlainTextEmail(String host, String port, final String userName, final String password, String toAddress,
			String subject, String message) throws AddressException,MessagingException 
	{
        // sets SMTP server properties
        Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setText(message);
        // sends the e-mail
        Transport.send(msg);
    }

    /**
     * Send email.
     *
     * @param mailTo the mail to
     * @param subject the subject
     * @param message the message
     */
    public static void sendEmail(String mailTo,String subject,String message) {
        // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "icmgroup17@gmail.com";
        String password = "Aa123456!";
        // outgoing message information
        SendMailController mailer = new SendMailController();
        try {
            mailer.sendPlainTextEmail(host, port, mailFrom, password, mailTo, subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }
}
