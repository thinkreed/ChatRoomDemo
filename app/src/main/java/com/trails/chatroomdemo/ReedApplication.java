package com.trails.chatroomdemo;

import android.app.Application;

/**
 * Created by huweijie on 16/5/4.
 */
public class ReedApplication extends Application {

  private static ReedApplication sReedApplicationInstance;

  public ReedApplication() {
    sReedApplicationInstance = this;
  }

  public static ReedApplication getInstance() {
    return sReedApplicationInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
  }

}
