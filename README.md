What's My Ip?
==============
This project consists of a set of scripts that perform the following tasks:

* Obtains the current IP address of the server where it is running
* Looks up the most recent IP address of the server in a log file
* If the current IP address is different that the most recent IP address:
  * Updates the log file with the current IP address
  * Send the new IP address in a customizable email to a configurable address
* If the IP address's are the same, it does nothing. 

This project was born out of my necessity to keep up with my ever changing IP address after switching ISP's earlier this year. There are several services running at home that I need access to on a daily basis.  If my IP changes over night, after a brown out, or for some other reason, I need to know about it asap.

There are two different implementations of this project, one in Ruby, and one in Groovy.

* master branch: groovy
* ruby branch: ruby (obviously)

Requirements
------------
* Recent version of [Ruby][] installed and configured properly.
* Actionmailer Ruby Gem installed.

Configuration
-------------
Update places in mailer.rb marked with '<-- update' to match your environmental settings.

    class Mailer < ActionMailer::Base
	  def ip_address_change_message(ip_address)
	    from 'dns-change@someserver.org' <-- update
	    recipients 'me@gmail.com' <-- update
	    subject "IP Address Changed to #{ip_address}" 
	    body "IP Address changed to #{ip_address}.\nPlease update your configurations."
	  end
	end

	ActionMailer::Base.smtp_settings = {
	        :address => 'smtp.someserver.org',  <-- update
	        :port => 25, <-- update
	        :domain => 'someserver.org', <-- update
	        :user_name => 'dns-change@someserver.org', <-- update
	        :password => 'password', <-- update
	        :authentication => :login
	}
	


Running 
-------
Change in to the project directory and run the following command:

    ./whatsmyip.rb 

Better Yet: Run as a daily cron job
----------
Set this up as a [cron][] job (in a mac/linux environment) by adding the following line to your crontab (runs every day at 9a and 12p):

    0 09,12 *  *  *  ruby whatsmyip.rb >> /var/logs/my-ip.log

Reference: [Crontab Guide][]

[Crontab Guide]: http://www.crontabrocks.org
[Ruby]: http://www.ruby-lang.org/en/
[cron]: http://en.wikipedia.org/wiki/Cron