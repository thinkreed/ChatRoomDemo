package com.trails.chatroomdemo.model;

/**
 * Created by thinkreed on 16/5/4.
 */
public class Model {
  public String messageContent;
  public Templete templete;

  private Model(Builder builder) {
    this.messageContent = builder.messageContent;
    this.templete = builder.templete;
  }

  public static final class Builder {
    String messageContent;
    Templete templete;

    public Builder messageContent(String messageContent) {
      this.messageContent = messageContent;
      return this;
    }

    public Builder templete(Templete templete) {
      this.templete = templete;
      return this;
    }

    public Model build() {
      return new Model(this);
    }
  }

  public enum Templete {
    MESSAGE_MY,
    MESSAGE_OTHERS
  }
}
