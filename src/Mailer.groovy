import javax.mail.Session
import javax.mail.Message
import javax.mail.internet.MimeMessage
import javax.mail.internet.InternetAddress

@Grapes([
  @Grab(group = 'javax.activation', module = 'activation', version = '1.1'),
  @Grab(group = 'javax.mail', module = 'mail', version = '1.4')
])

/**
 * Created by IntelliJ IDEA.
 * User: Steve
 * Date: Jun 19, 2009
 * Time: 3:15:39 PM
 */

class Mailer {

  static def s_config = new ConfigSlurper("message").parse(new File('MailProperties.groovy').toURL())

  static def deliverIpAddressChangeMessage(ipAddress) {
    def subject = "IP Address Changed to ${ipAddress}"
    def message = "IP Address changed to ${ipAddress}.\nPlease update your configurations."
    sendMail("${s_config.message.to}".toString(), "${s_config.message.from}".toString(), subject, message)
  }

  static private sendMail(to, from, subject, message) {
    def session = Session.getDefaultInstance(s_config.toProperties(), null)

    def mimeMessage = new MimeMessage(session)
    mimeMessage.setRecipients Message.RecipientType.TO, to
    mimeMessage.setSubject subject
    mimeMessage.setFrom new InternetAddress(from)
    mimeMessage.setContent message.toString(), "text/plain"

    def transport = session.getTransport("smtp")
    transport.connect "${s_config.mail.username}".toString(), "${s_config.mail.password}".toString()
    transport.sendMessage mimeMessage, mimeMessage.allRecipients
  }

}