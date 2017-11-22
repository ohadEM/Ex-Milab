package com.example.ex_4;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import layout.QuotesService;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        final EditText editText = (EditText)view.findViewById(R.id.edit_text);
        Button button = (Button)view.findViewById(R.id.quote_btn);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), QuotesService.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, intent,0);
                AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), R.id.edit_text *60000, pendingIntent);

              /*  QuotesService.startActionFoo(,R.id.edit_text);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(v.getContext())
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Quote")
                        .setContentText(editText.getText().toString());
                NotificationManager notificationManager = (NotificationManager)v.getContext()
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                int id = 1;
                notificationManager.notify(id, builder.build());
            */
            }
        });
        return view;
    }
}
