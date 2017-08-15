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
 * @author D. Ashmore
 *
 */
public class JMXRPApplication {

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
