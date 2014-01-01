require 'rubygems'
require 'action_mailer'

class MyMailer < ActionMailer::Base
  default from: 'dns-change@example.com'
  default to: 'someone@example.com'

  def deliver_ip_address_change_message(ip_address)
    mail(subject: "IP Address Changed to #{ip_address}",
         body: "IP Address changed to #{ip_address}.\nPlease update your configurations."
         ).deliver
    puts "Message Delivered"
  end
end

ActionMailer::Base.smtp_settings = {
  address:              'smtp.gmail.com',
  port:                 587,
  domain:               'example.com',
  user_name:            '<username>',
  password:             '<password>',
  authentication:       'plain',
  enable_starttls_auto: true  
}
