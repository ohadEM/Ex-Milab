package com.example.client;


import android.app.Application;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URISyntaxException;

import io.socket.emitter.Emitter;
import io.socket.client.Socket;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EditText coinNameText;
    Button notificationBtn;
    TextView coinQuoteText;

    private Socket mSocket;
    private String coinName;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSocket = (CoinApplication)getActivity().getApplication().getSocket();
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on("postRate", onNewQuotes);
        mSocket.connect();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText coinNameText = (EditText)view.findViewById(R.id.coinNameText);
        final Button notificationBtn = (Button)view.findViewById(R.id.notification_Button);
        final TextView coinQuoteText = (TextView)view.findViewById(R.id.coinsQuotesText);

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinName = coinNameText.getText().toString();
                coinNameText.setText("");
            }
        });
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("MainActivityFragment", "Connection Success");
                    mSocket.emit("postRate", coinName);
                }
            });
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("MainActivityFragment", "Connection Error");
                }
            });
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("MainActivityFragment", "Disconnected");
                }
            });
        }
    };

    private Emitter.Listener onNewQuotes = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String msg = args[0].toString();
                    Log.d("MainActivityFragment", "message: " + msg);
                    coinQuoteText.setText(msg);
                }
            });
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.off("postRate", onNewQuotes);
}


public class CoinApplication extends Application {


    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://localehost:5000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}