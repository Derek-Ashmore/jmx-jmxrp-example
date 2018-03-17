package org.force66.jmxrp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.management.MBeanServer;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Example Java application publishing a JMX port over the JMXRP protocol.
 * 
 * <p>The service URL should be of the format: <code>service:jmx:jmxmp://host:port</code></p>
 * <p>Any JMX clients connecting via JMXMP must have a client implementation of the JMXMP protocol in their class path. The only one at 
 * this time is <code>opendmk_jmxremote_optional_jar</code> available through Maven Central.</p>
 * <p>Patterned after this <a href="https://stackoverflow.com/questions/44953031/how-to-authenticate-with-user-and-password-using-custom-jmx-server-using-tls-and">write-up</a></p>
 * <p>Documentation is old, but <a href="http://docs.oracle.com/cd/E19698-01/816-7609/6mdjrf86t/index.html">here</a></p>
 * @author D. Ashmore
 *
 */
public class JMXMPApplicationSecured {

  public static void main(String[] args) throws Exception {
    
    MBeanServer jmxMbean = ManagementFactory.getPlatformMBeanServer();
    JMXServiceURL jmxURL = new JMXServiceURL("jmxmp", null, 9010);
    JMXConnectorServer jmxFactory = 
        JMXConnectorServerFactory.newJMXConnectorServer(jmxURL, createEnvironmentMap(), jmxMbean);
    jmxFactory.start();
    
    // 10 minutes
    System.out.println("JMX Host has started.");
    Thread.sleep(60*1000*10);
    System.out.println("JMX Host has ended.");
    System.exit(0);

  }
  
  private static Map<String,Object> createEnvironmentMap() throws IOException {
    Map<String,Object> env = new HashMap<String,Object>();
    //env.put("jmx.remote.profiles", "SASL/PLAIN"); 
    //env.put("jmx.remote.sasl.callback.handler", 
    //    new SimpleCallbackHandler()); 
    //env.put(JMXConnector.CREDENTIALS, new String[] {"derek", "ashmore"});
    env.put("jmxserviceurl.jmxmp.username", "derek");
    env.put("jmxserviceurl.jmxmp.password", "ashmore");
    
    Properties accessProps = new Properties();
    accessProps.load(JMXMPApplicationSecured.class.getClassLoader().getResourceAsStream("access.properties"));
    File accessPropFile = File.createTempFile("jmxAccess", ",properties");
    accessProps.store(new FileOutputStream(accessPropFile), "JMX Access Rights");
    accessPropFile.deleteOnExit();
    //env.put("jmx.remote.x.access.file",accessPropFile.getCanonicalPath());
    
    return env;
  }

}
