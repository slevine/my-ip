import javax.mail.Session
import javax.mail.Message
import javax.mail.internet.MimeMessage
import javax.mail.internet.InternetAddress

/**
 * Created by IntelliJ IDEA.
 * User: Steve
 * Date: Jun 19, 2009
 * Time: 3:15:39 PM
 */

class Mailer {

  static def config = new ConfigSlurper("message").parse(new File('MailProperties.groovy').toURL())

  static def deliverIpAddressChangeMessage(ipAddress) {
    def subject = "IP Address Changed to ${ipAddress}"
    def message = "IP Address changed to ${ipAddress}.\nPlease update your configurations."
    sendMail("${config.message.to}".toString(), "${config.message.from}".toString(), subject, message)
  }

  static private sendMail(to, from, subject, message) {
    def session = Session.getDefaultInstance(config.toProperties(), null)

    def msg = new MimeMessage(session)
    msg.setRecipients Message.RecipientType.TO, to
    msg.setSubject subject
    msg.setFrom new InternetAddress(from)
    msg.setContent message.toString(), "text/plain"

    def t = session.getTransport("smtp")
    t.connect "${config.mail.username}".toString(), "${config.mail.password}".toString()
    t.sendMessage msg, msg.getAllRecipients()
  }

}