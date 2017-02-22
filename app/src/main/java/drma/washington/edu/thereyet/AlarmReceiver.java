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

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String message = bundle.getString("message");
        String phoneNumber = bundle.getString("phoneNumber");

        Log.i("AlarmReciever", "Intent Recieved at " + System.currentTimeMillis());
        Toast.makeText(context, phoneNumber + ": " + message, Toast.LENGTH_SHORT).show();

        if(app == null){
            Log.d("AlarmReciever", "Activity was null, creating it now");
            app = (AlarmApp) context.getApplicationContext();
        }

        app.getAlarmManager().setAlarm();
    }
}
