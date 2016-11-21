package com.cauealmeida.broadcastreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cauealmeida.broadcastreceiver.receiver.AlarmReceiver;

public class AlarmActivity extends AppCompatActivity {


    private EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        time = (EditText) findViewById(R.id.edtTime);
    }

    public void doConfig(View v) {
        String timeValue = time.getText().toString();
        int t = Integer.parseInt(timeValue);

        // Verify if timeValue is valid
        if (!timeValue.equals("")) {

            // Add pending intent to listen our broadcast
            Intent i = new Intent(this, AlarmReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this.getApplicationContext(),
                    1234, i, 0
            );

            // currentTimeMillis = device time at the moment
            // Every x time shoot PI
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + (t * 1000),
                    pendingIntent);

            Toast.makeText(this, "Time successfully configured to " + t + " seconds from now",
                    Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Add some time", Toast.LENGTH_SHORT).show();
        }
    }
}
