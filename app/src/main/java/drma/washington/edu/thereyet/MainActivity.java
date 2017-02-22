package drma.washington.edu.thereyet;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends Activity {

    EditText inputPhone;
    EditText inputMessage;

    TextWatcher phoneWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = (EditText) findViewById(R.id.inputMessage);
        inputPhone = (EditText) findViewById(R.id.inputPhone);

        String number = "1234567890";
        Log.i("Main Activity", number);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            number = PhoneNumberUtils.formatNumber(number, Locale.getDefault().getCountry());
            Log.i("MainActivity", "Number format set new");
        } else {
            number = PhoneNumberUtils.formatNumber(number); //Deprecated method
            Log.i("MainActivity", "Number format set old");
        }
        Log.i("Main Activity", number);

        phoneWatcher = new TextWatcher() {

            Boolean isPhoneEdit = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               if(!isPhoneEdit){
                   isPhoneEdit = true;



                   isPhoneEdit = false;
               }
            }
        };
        inputPhone.addTextChangedListener(phoneWatcher);

    }
}
