package com.example.isuru.des;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    private  static EditText uname;
    private static EditText pword;
    private static Button btnlogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        uname=(EditText)findViewById(R.id.editText3);
        pword=(EditText)findViewById(R.id.editText4);
        btnlogin=(Button)findViewById(R.id.button3);
       // loginbtn();
    }


    public void loginbtn(View view){
        String username=uname.getText().toString();
        String password=pword.getText().toString();
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        String type="Driver";
        backgroundWorker.execute(type,username,password);





    }
}
