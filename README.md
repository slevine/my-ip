What's My Ip?
==============
This project was born out necesity to keep up with my ever changing IP
address after switching ISP's earlier this year.

There are several services running at home that I need access to on a
daily basis.  If my ip changes over night, after a brown out, or for
some other reason, I need to know about it asap.

There are two different implmentations of this project, one in Ruby, and
one in Groovy.

* master branch: groovy
* ruby branch: ruby (obviously)


Requirements
------------
The only requirement for this project is a configured recent version of [Groovy][] [installed][].


Running
-------
Since this project is leveraging [Grape][] (The Groovy Adaptable Packaging Engine or Groovy Advanced Packaging Engine), the only thing you need to do to run this script is:

    groovy whatsMyIp.groovy

Better Yet: Run as a daily cron job
----------
Set this up as a [cron][] job (in a mac/linux environment) by adding the following line to your crontab (runs every day at 9a and 12p):

    0 09,12 *  *  *  groovy whatsMyIp.groovy >> /var/logs/my-ip.log

Reference: [Crontab Guide][]

[Grape]: http://groovy.codehaus.org/Grape
[Crontab Guide]: http://www.crontabrocks.org
[Groovy]: http://groovy.codehaus.org/Download
[installed]: http://groovy.codehaus.org/Installing+Groovy
[cron]: http://en.wikipedia.org/wiki/Cron