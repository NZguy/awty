package drma.washington.edu.thereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dandrew on 2/22/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String message = bundle.getString("message");
        String phoneNumber = bundle.getString("phoneNumber");

        Log.i("AlarmReciever", "Intent Recieved at " + System.currentTimeMillis());
        //Toast.makeText(context, phoneNumber + ": " + message, Toast.LENGTH_SHORT).show();
        sendMessage(message, phoneNumber, context);
        /**
         * Creating any more complicated toast seems to require access to the main activity
         * not sure how to do this yet, so I will hold off.
         *
         * Useful references
         * https://developer.android.com/guide/topics/ui/notifiers/toasts.html
         * http://stackoverflow.com/questions/16934425/call-an-activity-method-from-a-broadcastreceiver-class
         */

        AlarmApp app = (AlarmApp) context.getApplicationContext();
        app.getAlarmManager().setAlarm();
    }

    /**
     * Source for custom toast workaround
     * http://stackoverflow.com/questions/1618800/show-complex-toast-from-broadcastreceiver
     *
     * @param context
     */
    public void sendMessage(String message, String phoneNumber, Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast, null);
        TextView caption = (TextView) layout.findViewById(R.id.toast_caption);
        TextView body = (TextView) layout.findViewById(R.id.toast_body);
        caption.setText("Texting " + phoneNumber);
        body.setText(message);

        Toast toast = new Toast(context.getApplicationContext());
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

        String phoneDigits = phoneNumber.replaceAll("[^\\d]", "");

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneDigits));
//        intent.putExtra("sms_body", message);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneDigits, null, message, null, null);
    }
}
