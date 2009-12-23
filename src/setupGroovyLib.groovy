#!/usr/bin/env groovy

defaultArgs = ["javax.activation:activation:1.1", "javax.mail:mail:1.4"]
if (args.size() > 0 && args[0] == "-h") {
  println("Usage ${this.class.name} jarname [jarname...]")
  println("  The jarname is in format of Maven2 artifacts groupId:artifactId:version name.")
  println("  You may use site like http://www.mvnrepository.com to help you find the jarname.")
  println("  This script default arguments to:")
  println(defaultArgs.collect {"    " + it}.join("\n"))
  System.exit(1)
}
if (args.size() == 0) {
  args = defaultArgs
}
installPath = new File(System.properties['user.home'] + '/.groovy/lib')
if (!installPath.exists()) { installPath.mkdirs() }
existingLib = installPath.list()
repoUrl = "http://repo1.maven.org/maven2"
args.each {jarname ->
  names = jarname.split(/:/)
  groupId = names[0].split(/\./).join("/")
  artifactId = names[1]
  version = names[2]
  jarfile = "${artifactId}-${version}.jar"
  url = "${repoUrl}/${groupId}/${artifactId}/${version}/${jarfile}"
  if (existingLib.any { it =~ "${jarfile}" }) {
    println("${jarfile} already exists! skip download.")
  } else {
    println "Downloading ${url} as ${jarfile}"
    wget(url, new File(installPath, jarfile))
  }
}

def wget(url, dest) {
  new FileOutputStream(dest).withStream {out ->
    new URL(url).openStream().eachByte {
      out.write(it)
    }
  }
}
