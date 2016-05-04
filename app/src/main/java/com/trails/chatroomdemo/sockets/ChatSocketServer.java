package com.trails.chatroomdemo.sockets;

import android.util.Log;

import com.trails.chatroomdemo.utils.Consts;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by huweijie on 16/5/4.
 */
public class ChatSocketServer extends Thread {
  private boolean shouldStop = false;
  private ServerSocket mServerSocket;

  public ChatSocketServer(String name) {
    super(name);
  }

  @Override
  public void run() {
    super.run();
    try {
      mServerSocket = new ServerSocket(Consts.SOCKET_SERVER_PORT);
      while (!shouldStop) {
        Socket socket = mServerSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("we get " + message);
        ois.close();
        oos.close();
        socket.close();
      }
      mServerSocket.close();
    } catch (IOException ioException) {
      mServerSocket = null;
    } catch (ClassNotFoundException classNotFoundException) {
      Log.d(Consts.TAG, "read message error");
    }
  }

  public void exit() {
    this.shouldStop = true;
  }
}
