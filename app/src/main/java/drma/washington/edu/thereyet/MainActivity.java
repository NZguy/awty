package drma.washington.edu.thereyet;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {

    private EditText inputPhone;
    private EditText inputMessage;
    private Button startButton;

    private PendingIntent pendingIntent;

    private boolean isSending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = (EditText) findViewById(R.id.inputMessage);
        inputPhone = (EditText) findViewById(R.id.inputPhone);
        startButton = (Button) findViewById(R.id.btnStart);

        isSending = false;
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        inputPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSending();
            }
        });
    }

    private void toggleSending(){
        if(!isSending){
            if(inputsValid()){
                // If in not sending yet and inputs are valid
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                int interval = 4000;
                manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                        interval, pendingIntent);

                Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
                Log.i("MainActivity", "Alarm Set at " + System.currentTimeMillis());
                startButton.setText("Stop");
                isSending = true;
            }else{
                // If my input is not valid

            }
        }else{
            // If I want to stop the alarm
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);

            Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
            Log.i("MainActivity", "Alarm Cancelled at " + System.currentTimeMillis());
            startButton.setText("Start");
            isSending = false;
        }
    }

    private boolean inputsValid(){


        return true;
    }
}
