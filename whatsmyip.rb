#!/usr/bin/env ruby

require 'open-uri'
require 'mailer'

# IP Address Regex http://www.regular-expressions.info/examples.html
current_ip = open("http://whatsmyip.us").read.match(/\b\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\b/).to_s

puts "Current IP Address: #{current_ip}"

ip_log = File.open("ip-log.txt", "r+")

recent_ip = ip_log.readlines.last.split(",").last.chop!

if current_ip != recent_ip
  Mailer.deliver_ip_address_change_message(current_ip)
  puts "IP Address has changed, it is now: #{current_ip}. Sending Message."
  ip_log << ("#{Time.now},#{current_ip}\n")
end
