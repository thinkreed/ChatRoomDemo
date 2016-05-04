package com.trails.chatroomdemo.presenter;

import android.view.View;

import com.trails.chatroomdemo.model.Model;

/**
 * Created by huweijie on 16/5/4.
 */
public abstract class Presenter {

  View view;
  int id;

  protected abstract void bind(Model model);

  public int getId() {
    return id;
  }

  public View getView() {
    return view;
  }
}
