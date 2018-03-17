package org.force66.jmxrp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleCallbackHandler implements CallbackHandler {
  
  private static Logger logger = LoggerFactory.getLogger(SimpleCallbackHandler.class);
  private Properties passwordProperties = new Properties();
  
  public SimpleCallbackHandler() throws IOException {
    passwordProperties.load(this.getClass().getClassLoader().getResourceAsStream("jmxpassword.properties"));
  }

  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    logger.info("Number of callbacks provided = {}", (callbacks == null) ? 0 : callbacks.length);
    Arrays.stream(callbacks)
      .map(callback -> {
        return ToStringBuilder.reflectionToString(callback);
        })
      .forEach(callbackDesc -> {
        logger.info(callbackDesc);
      });;

  }

}
