package com.trails.chatroomdemo.model;

/**
 * Created by huweijie on 16/5/4.
 */
public class Model {
  public String messageContent;

  private Model(Builder builder) {
    this.messageContent = builder.messageContent;
  }

  public static final class Builder {
    String messageContent;

    public Builder messageContent(String messageContent) {
      this.messageContent = messageContent;
      return this;
    }

    public Model build() {
      return new Model(this);
    }
  }

  public enum templete {
    MESSAGE_MY,
    MESSAGE_OTHERS
  }
}
