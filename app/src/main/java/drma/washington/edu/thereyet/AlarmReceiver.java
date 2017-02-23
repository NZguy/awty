package drma.washington.edu.thereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by dandrew on 2/22/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private AlarmApp app;
    private MainActivity main;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String message = bundle.getString("message");
        String phoneNumber = bundle.getString("phoneNumber");

        Log.i("AlarmReciever", "Intent Recieved at " + System.currentTimeMillis());
        Toast.makeText(context, phoneNumber + ": " + message, Toast.LENGTH_SHORT).show();
        /**
         * Creating any more complicated toast seems to require access to the main activity
         * not sure how to do this yet, so I will hold off.
         *
         * Useful references
         * https://developer.android.com/guide/topics/ui/notifiers/toasts.html
         * http://stackoverflow.com/questions/16934425/call-an-activity-method-from-a-broadcastreceiver-class
         */

        if(app == null){
            Log.d("AlarmReciever", "Activity was null, creating it now");
            app = (AlarmApp) context.getApplicationContext();
        }

        app.getAlarmManager().setAlarm();
    }
}
