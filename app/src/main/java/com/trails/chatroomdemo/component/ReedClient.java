package com.trails.chatroomdemo.component;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.trails.chatroomdemo.utils.Consts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by huweijie on 16/5/18.
 */
public class ReedClient {

  private String mIpAddress;
  private PrintWriter printWriter;
  private BufferedReader bufferedReader;
  private Thread mThread;
  private HandlerThread mHandlerThread;
  private Handler mHandler;
  private Listener mListener;
  private Socket mSocket;

  public interface Listener {
    void onMessageReceived(String message);

    void onConnected();
  }

  public ReedClient(String ipAddress) {
    this.mIpAddress = ipAddress;
    mHandlerThread = new HandlerThread("reed");
    mHandlerThread.start();
    mHandler = new Handler(mHandlerThread.getLooper());
  }

  public void connect() {
    if (mThread != null && mThread.isAlive()) {
      return;
    }
    mThread = new Thread() {
      @Override
      public void run() {
        try {
          mSocket = new Socket();
          mSocket.setKeepAlive(true);
          SocketAddress address = new InetSocketAddress(mIpAddress, Consts.SOCKET_SERVER_PORT);
          mSocket.connect(address);
          printWriter = new PrintWriter(mSocket.getOutputStream(), true);
          bufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
          new Thread(new receive()).start();
        } catch (Exception e) {
          Log.e(Consts.TAG, e.getMessage());
        }
      }
    };
    mThread.start();
  }

  public void sendMessage(final String message) {
    if (printWriter != null) {
      mHandler.post(new Runnable() {
        @Override
        public void run() {
          printWriter.print(message);
          Log.d(Consts.TAG, "write result : " + !printWriter.checkError());
        }
      });
    }
  }

  public void setListener(Listener listener) {
    this.mListener = listener;
  }

  public void disconnect() {
    mHandler.post(new Runnable() {
      @Override
      public void run() {
        try {
          mSocket.close();
        } catch (Exception e) {
          Log.d(Consts.TAG, e.getMessage());
        }
      }
    });

  }

  private class receive implements Runnable {
    @Override
    public void run() {
      if (bufferedReader != null) {
        while (true) {
          try {
            String line = bufferedReader.readLine();
            Log.d(Consts.TAG, line == null ? "line = null" : line);
            if (line != null && mListener != null) {
              mListener.onMessageReceived(line);
            }
          } catch (Exception e) {
            Log.e(Consts.TAG, e.getMessage());
            break;
          }
        }
      }
    }
  }
}
