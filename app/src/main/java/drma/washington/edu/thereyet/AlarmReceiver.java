package drma.washington.edu.thereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by dandrew on 2/22/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("AlarmReciever", "Intent Recieved at " + System.currentTimeMillis());
        Toast.makeText(context, "Intent Recieved", Toast.LENGTH_SHORT).show();
    }
}
