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
    private AlarmApp app;

    private EditText inputPhone;
    private EditText inputMessage;
    private EditText inputInterval;
    private Button startButton;

    private boolean isSending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = (EditText) findViewById(R.id.inputMessage);
        inputPhone = (EditText) findViewById(R.id.inputPhone);
        inputInterval = (EditText) findViewById(R.id.inputInterval);
        startButton = (Button) findViewById(R.id.btnStart);

        isSending = false;
        app = (AlarmApp) getApplicationContext();

        inputPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSending();
            }
        });
    }

    private void toggleSending(){
        String message = inputMessage.getText().toString();
        String phoneNumber = inputPhone.getText().toString();
        String interval = inputInterval.getText().toString();

        if(!isSending){
            if(inputsValid(message, phoneNumber, interval)){
                // If in not sending yet and inputs are valid
                app.getAlarmManager().setVariables(message, phoneNumber,
                    Integer.parseInt(interval) * (1000));
                app.getAlarmManager().setAlarm();

                Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
                Log.i("MainActivity", "Alarm Set at " + System.currentTimeMillis());
                startButton.setText("Stop");
                isSending = true;
            }else{
                // If my input is not valid
                Toast.makeText(this, "Inputs are invalid", Toast.LENGTH_SHORT).show();
            }
        }else{
            // If I want to stop the alarm
            app.getAlarmManager().stopAlarm();

            Toast.makeText(this, "Alarm Stopped", Toast.LENGTH_SHORT).show();
            Log.i("MainActivity", "Alarm Stopped at " + System.currentTimeMillis());
            startButton.setText("Start");
            isSending = false;
        }
    }

    private boolean inputsValid(String message, String phoneNumber, String interval){
        boolean isValid = true;

        if(message == null || message.isEmpty()){
            Log.d("MainActivity", "message is empty, not valid");
            // message is empty, not valid
            isValid = false;
        }
        if(phoneNumber == null || phoneNumber.isEmpty()){
            Log.d("MainActivity", "phoneNumber is empty, not valid");
            // phoneNumber is empty, not valid
            // This needs to be redone when getting an actual number
            isValid = false;
        }
        int intInterval;
        try{
            intInterval = Integer.parseInt(interval);
        }catch (NumberFormatException e){
            Log.d("MainActivity", "The interval wasnt a number, invalid");
            // The interval wasnt a number, invalid
            isValid = false;
        }

        return isValid;
    }
}
