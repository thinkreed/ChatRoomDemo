package com.trails.chatroomdemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by thinkreed on 16/5/4.
 */
public class ReedApplication extends Application {

  private static ReedApplication sReedApplicationInstance;

  public ReedApplication() {
    sReedApplicationInstance = this;
  }

  public static ReedApplication getInstance() {
    return sReedApplicationInstance;
  }

  private void initializeFresco() {
    Fresco.initialize(this);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initializeFresco();
  }

}
