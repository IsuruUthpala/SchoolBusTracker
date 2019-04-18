package com.example.isuru.des;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toast.makeText(Main3Activity.this,"Login Success",Toast.LENGTH_LONG).show();
        addListnerOnButton();
        btnclickmethod();
        btnclickmethodx();
       // showMap();
        btmap();
    }



    private Button button;
    private static WebView browser;

    public void addListnerOnButton(){
        button=(Button)findViewById(R.id.button4);
        //browser=(WebView)findViewById(R.id.webs2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String url="https://www.google.com/maps/@6.9813882,81.0809846,19z";
                browser.getSettings().setLoadsImagesAutomatically(true);
                browser.getSettings().setJavaScriptEnabled(true);
                browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                browser.loadUrl(url);*/
                Intent intent=new Intent("com.example.isuru.des.MainActivity");
                startActivity(intent);


            }
        });


    }
    private Button button2;
    public void btnclickmethod(){

        button2=(Button)findViewById(R.id.button5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.isuru.des.Main4Activity");
                startActivity(intent);

            }
        });


    }

    private Button buttonx;
    public void btnclickmethodx(){

        buttonx=(Button)findViewById(R.id.button9);
        buttonx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.isuru.des.Main8Activity");
                startActivity(intent);

            }
        });


    }
    private Button buttonmp;
    public void btmap(){

        buttonmp=(Button)findViewById(R.id.button2);
        buttonmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent("com.example.isuru.des.Main5Activity");
                startActivity(intent);

            }
        });


    }
    private  Button button6;




}
