package com.trails.chatroomdemo.presenter;

import android.view.View;
import android.widget.TextView;

import com.trails.chatroomdemo.R;
import com.trails.chatroomdemo.model.Model;

/**
 * Created by thinkreed on 16/5/4.
 */
public class DisplayPresenter extends Presenter {
  @Override
  protected void bind(Model model) {
    Object content = getContent(model);
    if (content == null) {
      getView().setVisibility(View.GONE);
    }
    if (content instanceof String && getView() instanceof TextView) {
      ((TextView) getView()).setText((String) content);
    }
  }

  private Object getContent(Model model) {
    switch (getId()) {
      case R.id.message_content:
        return model.messageContent;
      default:
        return null;
    }
  }
}
