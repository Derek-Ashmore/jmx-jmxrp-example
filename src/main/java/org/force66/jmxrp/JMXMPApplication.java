package org.force66.jmxrp;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Example Java application publishing a JMX port over the JMXRP protocol.
 * 
 * <p>The service URL should be of the format: <code>service:jmx:jmxmp://host:port</code></p>
 * <p>Any JMX clients connecting via JMXMP must have a client implementation of the JMXMP protocol in their class path. The only one at 
 * this time is <code>opendmk_jmxremote_optional_jar</code> available through Maven Central.</p>
 * @author D. Ashmore
 *
 */
public class JMXMPApplication {

  public static void main(String[] args) throws Exception {
    
    MBeanServer jmxMbean = ManagementFactory.getPlatformMBeanServer();
    JMXServiceURL jmxURL = new JMXServiceURL("jmxmp", null, 9010);
    JMXConnectorServer jmxFactory = JMXConnectorServerFactory.newJMXConnectorServer(jmxURL, null, jmxMbean);
    jmxFactory.start();
    
    // 10 minutes
    System.out.println("JMX Host has started.");
    Thread.sleep(60*1000*10);
    System.out.println("JMX Host has ended.");

  }

}
