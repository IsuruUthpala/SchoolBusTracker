package com.example.isuru.des;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main7Activity extends AppCompatActivity {
EditText feed;
//String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        feed=(EditText)findViewById(R.id.editTextt);
    }

    public void feedback(View view){
        String msg=feed.getText().toString();

        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        String type="feedback";

        backgroundWorker.execute(type,msg);



    }
}
