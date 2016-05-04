package com.trails.chatroomdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trails.chatroomdemo.R;
import com.trails.chatroomdemo.utils.Consts;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          for (int i = 0; i < 5; i++) {
            Socket socket =
                new Socket(InetAddress.getByName("127.0.0.1"), Consts.SOCKET_SERVER_PORT);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("we say " + i);
            Log.d(Consts.TAG, "we say log " + i);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            Log.d(Consts.TAG, "we get from server " + message);
            oos.close();
            ois.close();
            socket.close();
          }
        } catch (UnknownHostException e) {
          e.printStackTrace();
        } catch (IOException e) {
          Log.d(Consts.TAG, "2333");
        } catch (ClassNotFoundException e) {
          Log.d(Consts.TAG, "heihei");
        }
      }
    }).start();
  }
}
