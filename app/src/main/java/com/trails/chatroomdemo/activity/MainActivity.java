package com.trails.chatroomdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.trails.chatroomdemo.R;
import com.trails.chatroomdemo.adapter.ChatMessageAdapter;
import com.trails.chatroomdemo.component.ReedClient;
import com.trails.chatroomdemo.model.Model;

public class MainActivity extends AppCompatActivity {

  private RecyclerView mList;
  private ChatMessageAdapter mAdapter;
  private Handler mHandler = new Handler();
  private View mBtnSend;
  private EditText mEditText;
  private ReedClient mClient;

  private ReedClient.Listener mListener = new ReedClient.Listener() {
    @Override
    public void onMessageReceived(final String message) {
      mHandler.post(new Runnable() {
        @Override
        public void run() {
          mAdapter.appendData(new Model.Builder().messageContent(message).build());
        }
      });
    }

    @Override
    public void onConnected() {

    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mList = (RecyclerView) findViewById(R.id.list);
    mEditText = (EditText) findViewById(R.id.edit_message);
    mBtnSend = findViewById(R.id.btn_send);
    mBtnSend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mEditText.getText().length() > 0) {
          mClient.sendMessage(mEditText.getText().toString() + "\n");
          mEditText.setText("");
        }
      }
    });
    if (mList != null) {
      mList.setLayoutManager(new LinearLayoutManager(this));
      mAdapter = new ChatMessageAdapter();
      mList.setAdapter(mAdapter);
    }

    mClient = new ReedClient("192.168.1.220");
    mClient.setListener(mListener);
    mClient.connect();
  }

  @Override
  protected void onDestroy() {
    mClient.disconnect();
    super.onDestroy();
  }
}
