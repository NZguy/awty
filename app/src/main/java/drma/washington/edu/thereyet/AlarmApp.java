package drma.washington.edu.thereyet;

import android.app.Application;

/**
 * Created by dandrew on 2/22/2017.
 */

public class AlarmApp extends Application {
    private ExactAlarmManager alarmManager;

    @Override
    public void onCreate() {
        super.onCreate();
        alarmManager = new ExactAlarmManager(this);
    }

    public ExactAlarmManager getAlarmManager(){
        return alarmManager;
    }
}
