package com.trails.chatroomdemo.presenter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trails.chatroomdemo.adapter.ChatMessageAdapter;
import com.trails.chatroomdemo.model.Model;

/**
 * Created by huweijie on 16/5/4.
 */
public class ViewGroupPresenter {

  public ViewGroup rootView;
  public ChatMessageAdapter.ViewHolder holder;
  private SparseArray<Presenter> mChildPresenters = new SparseArray<>();

  public ViewGroupPresenter(ViewGroup parent, Context context, int layoutRes) {
    rootView = (ViewGroup) LayoutInflater.from(context).inflate(layoutRes, parent, false);
  }

  public void bind(Model model) {
    for (int i = 0; i < mChildPresenters.size(); i++) {
      Presenter presenter = findChildPresenter(i);
      if (presenter != null) {
        presenter.bind(model);
      }
    }
  }

  public Presenter findChildPresenter(int index) {
    int viewId = mChildPresenters.keyAt(index);
    View childView = rootView.findViewById(viewId);
    if (childView == null) {
      return null;
    } else {
      Presenter presenter = mChildPresenters.valueAt(index);
      presenter.view = childView;
      presenter.id = viewId;
      return presenter;
    }
  }

  public ViewGroupPresenter add(Presenter presenter, int viewId) {
    mChildPresenters.put(viewId, presenter);
    return this;
  }
}
