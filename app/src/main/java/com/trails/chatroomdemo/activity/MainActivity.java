package com.trails.chatroomdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.trails.chatroomdemo.R;
import com.trails.chatroomdemo.adapter.ChatMessageAdapter;
import com.trails.chatroomdemo.utils.Consts;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

  private RecyclerView mList;
  private ChatMessageAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mList = (RecyclerView) findViewById(R.id.list);
    if (mList != null) {
      mList.setLayoutManager(new LinearLayoutManager(this));
      mAdapter = new ChatMessageAdapter();
      mList.setAdapter(mAdapter);
    }
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          for (int i = 0; i < 5; i++) {
            Socket socket =
                new Socket(InetAddress.getByName("127.0.0.1"), Consts.SOCKET_SERVER_PORT);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("we say " + i);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            oos.close();
            ois.close();
            socket.close();
          }
        } catch (UnknownHostException e) {
          Log.d(Consts.TAG, "unknow host");
        } catch (IOException e) {
          Log.d(Consts.TAG, "io exception");
        } catch (ClassNotFoundException e) {
          Log.d(Consts.TAG, "class not found");
        }
      }
    }).start();
  }
}
