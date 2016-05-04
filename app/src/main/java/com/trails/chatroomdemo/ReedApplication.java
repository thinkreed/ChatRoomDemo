package com.trails.chatroomdemo;

import android.app.Application;

import com.trails.chatroomdemo.sockets.ChatSocketServer;

/**
 * Created by huweijie on 16/5/4.
 */
public class ReedApplication extends Application {

  private static ReedApplication sReedApplicationInstance;
  private ChatSocketServer mChatSocketServer;

  public ReedApplication() {
    sReedApplicationInstance = this;
  }

  public static ReedApplication getInstance() {
    return sReedApplicationInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initializeSocketServer();
  }

  private void initializeSocketServer() {
    mChatSocketServer = new ChatSocketServer("chat-server");
    mChatSocketServer.start();
  }

  public ChatSocketServer getChatSocketServer() {
    return mChatSocketServer;
  }
}
