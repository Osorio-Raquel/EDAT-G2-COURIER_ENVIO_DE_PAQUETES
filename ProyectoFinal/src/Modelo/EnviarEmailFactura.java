package Modelo;

import java.io.File;
import java.util.Properties;
import javax.mail.Message;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class EnviarEmailFactura {
    private static String emailFrom = "grupocourier2@gmail.com";
    private static String passwordFrom = "nzqw fjil foin eqxu"; // Contraseña o Contraseña de aplicación
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public String correoDestino;

    public void setCorreoDestino(String correoDestino) {
        this.correoDestino = correoDestino;
    }

    public String getCorreoDestino() {
        return correoDestino;
    }

    public EnviarEmailFactura() {
        this.correoDestino = "raquel707osorio@gmail.com"; // Cambia esta dirección según sea necesario
        mProperties = new Properties();
    }

    public void EnviarCorreo() {
        crearEmail();
        sendEmail();
    }

    public void crearEmail() {
        emailTo = correoDestino;
        subject = "FACTURA";
        content = "Agradecemos su confianza! le informaremos del estado de su paquete que tenga un lindo dia!.";

        // Configuración de SMTP de Gmail
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");  // Habilitar STARTTLS
        mProperties.setProperty("mail.smtp.port", "587");  // Usar puerto 587 para TLS
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.auth", "true");
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");  // Forzar TLS 1.2

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);

            // Cuerpo del mensaje en formato HTML
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String content = "<html>"
                           + "<head>"
                           + "<style>"
                           + "body { font-family: Arial, sans-serif; color: #333; line-height: 1.6; }"
                           + "h1 { color: #4CAF50; font-size: 24px; }"
                           + "p { font-size: 16px; }"
                           + "table { width: 100%; border-collapse: collapse; margin-top: 20px; }"
                           + "table, th, td { border: 1px solid #ddd; }"
                           + "th { background-color: #4CAF50; color: white; padding: 8px; }"
                           + "td { padding: 8px; text-align: center; }"
                           + "</style>"
                           + "</head>"
                           + "<body>"
                           + "<h1>¡Gracias por tu compra!</h1>"
                           + "<p>Estimado/a Raquel,</p>"
                           + "<p>Nos complace informarte que tu envio ha sido procesado exitosamente. A continuación, te presentamos los detalles de tu compra:</p>"
                           + "<table>"
                           + "<tr><th>Producto</th><th>Cantidad</th><th>Precio</th></tr>"
                           + "<tr><td>envio 1</td><td>2</td><td>$20.00</td></tr>"
                           + "<tr><td>envio 2</td><td>1</td><td>$15.00</td></tr>"
                           + "</table>"
                           + "<p><strong>Total: $55.00</strong></p>"
                           + "<p>Gracias por elegirnos. Si tienes alguna pregunta, no dudes en contactarnos.</p>"
                           + "<p>Atentamente,<br>El equipo de [Nombre de la Empresa]</p>"
                           + "</body>"
                           + "</html>";

            messageBodyPart.setContent(content, "text/html; charset=UTF-8");

            // Adjuntar archivo
            File file = new File("C:/Documentos/Factura.pdf");
            if (file.exists()) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                FileDataSource source = new FileDataSource(file);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(file.getName());

                // Combinar las partes en un multipart
                MimeMultipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentBodyPart);

                mCorreo.setContent(multipart);
            }

        } catch (AddressException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }


    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
