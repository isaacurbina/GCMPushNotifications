package com.mac.isaac.gcmpushnotificationsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pushbots.push.Pushbots;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pushbots.sharedInstance().init(this);
        Pushbots.sharedInstance().setCustomHandler(CustomHandler.class);
        textView = (TextView) findViewById(R.id.textView);
        if (getIntent().hasExtra("pushMessage")) {
            String message = getIntent().getExtras().getString("pushMessage").toString();
            if (message.contains("http://")) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(message));
                startActivity(i);
            } else if (message.contains(".class")){
                Intent i = new Intent(this, SecondActivity.class);
                startActivity(i);
            } else {
                Log.d("MYTAG", message);
                textView.setText(message);
            }
        }
    }
}
