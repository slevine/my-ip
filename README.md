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
The only requirement to run this is to have a recent version of [Groovy][] [installed][] and configured properly.

Configuration
-------------
Update places in MailProperties.groovy marked with '<-- update' to match your environmental settings.

    mail {
      smtp.auth = 'true'
      host = 'somehost.org' <-- update
      username = 'dns-change@somehost.org' <-- update
      password = 'password' <-- update
    }

    message {
      to = 'me@me.com' <-- update
      from = 'dns-change@somehost.org' <-- update
    }


Running 
-------
Since this project is leveraging [Grape][] (The Groovy Adaptable Packaging Engine or Groovy Advanced Packaging Engine), the only thing you need to do to run this script is change in to the directory containing the script and invoke:

    ./whatsMyIp.groovy 

Note: If you are unable to invoke the command make sure it has execute permissions (755). 

On the Windows platform you need to invoke it with:
  
    groovy whatsMyIp.groovy

Better Yet: Run as a daily Cron job
----------
Set this up as a [cron][] job (in a mac/linux environment) by adding the following line to your crontab (runs every day at 9a and 12p):

    0 09,12 *  *  *  groovy whatsMyIp.groovy >> /var/logs/my-ip.log

Reference: [Crontab Guide][]

[Grape]: http://groovy.codehaus.org/Grape
[Crontab Guide]: http://www.crontabrocks.org
[Groovy]: http://groovy.codehaus.org/Download
[installed]: http://groovy.codehaus.org/Installing+Groovy
[cron]: http://en.wikipedia.org/wiki/Cron