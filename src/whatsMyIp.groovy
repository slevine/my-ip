#!/usr/bin/env groovy

/**
 * Created by IntelliJ IDEA.
 * User: Steve
 * Date: Jun 13, 2009
 * Time: 4:22:56 PM
 */


// IP Address Regex http://www.regular-expressions.info/examples.html
currentIp = ("http://whatsmyip.us/".toURL().text =~ /\b\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\b/)[0]

println currentIp

File ipLog = new File("ip-log.txt")

recentIp = ipLog.readLines().last().tokenize(",").last().trim()

if (currentIp != recentIp) {
  Mailer.deliverIpAddressChangeMessage currentIp
  println "IP Address has changed, it is now: ${currentIp}. Sending Message."
  ipLog << "${new Date()}, ${currentIp}\n"
}





