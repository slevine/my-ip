require 'rubygems'
require 'action_mailer'

class Mailer < ActionMailer::Base
  def ip_address_change_message(ip_address)
    from 'dns-change@someserver.org'
    recipients 'me@gmail.com'
    subject "IP Address Changed to #{ip_address}"
    body "IP Address changed to #{ip_address}.\nPlease update your configurations."
  end
end

ActionMailer::Base.smtp_settings = {
        :address => 'smtp.someserver.org',
        :port => 25,
        :domain => 'someserver.org',
        :user_name => 'dns-change@someserver.org',
        :password => 'password',
        :authentication => :login
}