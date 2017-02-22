package drma.washington.edu.thereyet;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
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

        inputPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }
}
