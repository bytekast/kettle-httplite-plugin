package org.pentaho.di.core.httplite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
public class MyStepDialogBridge {

  @Autowired
  @Qualifier("requestChannel")
  private DirectChannel requestChannel;

  @Autowired
  @Qualifier("replyChannel")
  private DirectChannel replyChannel;

  @Autowired
  @Qualifier("uiChannel")
  private DirectChannel uiChannel;

  public DirectChannel getRequestChannel() {
    return requestChannel;
  }

  public void setRequestChannel(DirectChannel requestChannel) {
    this.requestChannel = requestChannel;
  }

  public DirectChannel getReplyChannel() {
    return replyChannel;
  }

  public void setReplyChannel(DirectChannel replyChannel) {
    this.replyChannel = replyChannel;
  }

  public DirectChannel getUiChannel() {
    return uiChannel;
  }

  public void setUiChannel(DirectChannel uiChannel) {
    this.uiChannel = uiChannel;
  }
}
