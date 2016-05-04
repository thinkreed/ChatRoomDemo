package com.trails.chatroomdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.trails.chatroomdemo.R;
import com.trails.chatroomdemo.model.Model;
import com.trails.chatroomdemo.presenter.DisplayPresenter;
import com.trails.chatroomdemo.presenter.ViewGroupPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huweijie on 16/5/4.
 */
public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {

  private List<Model> mDataList = new ArrayList<>();

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(createViewGroupPresenter(parent, parent.getContext(), viewType));
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Model model = mDataList.get(position);
    holder.presenter.holder = holder;
    holder.presenter.bind(model);
  }

  @Override
  public int getItemCount() {
    return mDataList.size();
  }

  public void setDataList(List<Model> dataList) {
    mDataList = dataList;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public final ViewGroupPresenter presenter;

    public ViewHolder(ViewGroupPresenter viewGroupPresenter) {
      super(viewGroupPresenter.rootView);
      presenter = viewGroupPresenter;
    }
  }

  protected ViewGroupPresenter createViewGroupPresenter(ViewGroup parent, Context context,
      int viewType) {
    switch (Model.templete.values()[viewType]) {
      case MESSAGE_MY:
        return new ViewGroupPresenter(parent, context, R.layout.item_message_my)
            .add(new DisplayPresenter(), R.id.message_content);
      case MESSAGE_OTHERS:
        return new ViewGroupPresenter(parent, context, R.layout.item_message_others)
            .add(new DisplayPresenter(), R.id.message_content);
      default:
        return null;
    }
  }
}
