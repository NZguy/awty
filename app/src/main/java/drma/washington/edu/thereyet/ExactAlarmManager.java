package drma.washington.edu.thereyet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by dandrew on 2/22/2017.
 */

public class ExactAlarmManager {
    private Context context;
    private AlarmManager manager;
    private PendingIntent pendingIntent;
    private int interval; // How often the intent will be broadcast
    private String message;
    private String phoneNumber;

    public ExactAlarmManager(Context context){
        this.context = context;
        this.manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Default values
        this.interval = 4000;
        this.message = "Default message";
        this.phoneNumber = "(123)456-7890";

        // Intent to be sent
        //createIntent();
    }

    private void createIntent(){
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("message", message);
        alarmIntent.putExtra("phoneNumber", phoneNumber);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
    }

    public void setVariables(String message, String phoneNumber, int interval){
        this.interval = interval;
        this.message = message;
        this.phoneNumber = phoneNumber;
        createIntent();
    }

    public void setAlarm(){
        Log.d("AlarmManager", "Setting next alarm");
        manager.set(AlarmManager.RTC_WAKEUP, (System.currentTimeMillis() + interval), pendingIntent);
    }

    public void stopAlarm(){
        Log.d("AlarmManager", "Stopping alarm");
        manager.cancel(pendingIntent);
    }

}
