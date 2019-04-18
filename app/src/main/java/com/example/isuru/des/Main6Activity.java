package com.example.isuru.des;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        btmap2();
        btfeed();
        btnotify();
    }




    private Button buttonmp2;
    public void btmap2(){

        buttonmp2=(Button)findViewById(R.id.button6);
        buttonmp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent("com.example.isuru.des.Main5Activity");
                startActivity(intent);

            }
        });


    }
    private Button btfeed1;
    public void btfeed(){

        btfeed1=(Button)findViewById(R.id.button8);
        btfeed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent("com.example.isuru.des.Main7Activity");
               // Intent intent=new Intent("com.example.isuru.des.Notify");
                startActivity(intent);

            }
        });


    }

    private Button btnoti;
    public void btnotify(){

        btnoti=(Button)findViewById(R.id.button13);
        btnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent=new Intent("com.example.isuru.des.Main7Activity");
                Intent intent=new Intent("com.example.isuru.des.Notify");
                startActivity(intent);

            }
        });


    }


}
