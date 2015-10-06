@Grab('org.apache.camel:camel-core:2.10.0')
@Grab('org.slf4j:slf4j-simple:1.6.6')
@Grab(group="org.apache.camel",module="camel-jetty",version="2.10.0")

import org.apache.camel.*
import org.apache.camel.impl.*
import org.apache.camel.builder.*

def camelContext = new DefaultCamelContext()
camelContext.addRoutes(new RouteBuilder() {

    int num = 0

    def number = {
      println( "incrementing num" )
       ++num 
    }

    def void configure() {

      from( "jetty:http://0.0.0.0:8090/increment" )
           .process( new Processor()  { 
               def void process(Exchange exchange) {
                  println "You called me ${number()} times, exchange= $exchange "
               } 
            })


       from("timer://jdkTimer?period=3000")
           .to("log://camelLogger?level=INFO")
           .process(new Processor() {
                def void process(Exchange exchange) {
                    println("Hello Camel World!")
                }
            })


    }


})
camelContext.start()

addShutdownHook{ camelContext.stop() }
synchronized(this){ this.wait() }
