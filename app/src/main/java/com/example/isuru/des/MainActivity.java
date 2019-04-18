package com.example.isuru.des;
import  com.example.isuru.des.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    EditText mobileno,message;
    Button sendsms;
    ArrayAdapter<String> adapter;
    String addr="http://trackmybusuwu.000webhostapp.com/getcont.php";
    InputStream is =null;
    String line=null;
    String result=null;
    JSONObject jo=null;
    String[] data;
    String[] cont;
    private  static  final int rpflr=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        getData();
       // adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        mobileno=(EditText)findViewById(R.id.editText1);
        message=(EditText)findViewById(R.id.editText2);
        sendsms=(Button)findViewById(R.id.button1);
        Spinner dropdown=findViewById(R.id.spinner);
        //String[] items=new String[]{"Nimal","Kamal","Wijesinghe","Raja","All"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,data);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        sendsms.setOnClickListener(new OnClickListener() {

           // @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View arg0) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

                    if(ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        getPem();
                    }
                    else{
                        if(shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)){

                            Toast.makeText(getApplicationContext(),"App required to access Location",Toast.LENGTH_SHORT).show();
                        }
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS},rpflr);
                    }
                }else{
                    getPem();
                }

            }
        });
    }

    public void getData(){
        try{
            URL url=new URL(addr);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());


        }catch (Exception e){
            // Toast.makeText(Main8Activity.this,"error1",Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }

        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){

                sb.append(line+"\n");

            }
            is.close();
            result=sb.toString();


        }
        catch (Exception e){
            e.printStackTrace();

            // Toast.makeText(Main8Activity.this,"error2",Toast.LENGTH_LONG).show();
        }

        try{
            //Toast.makeText(Main8Activity.this,result,Toast.LENGTH_LONG).show();
            JSONArray ja=new JSONArray(result);


            data=new String[ja.length()];
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                data[i]=jo.getString("name");



            }
            cont=new String[ja.length()];
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                cont[i]=jo.getString("contact");



            }




        }catch (Exception e){

            // Toast.makeText(Main8Activity.this,"error3",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }





    }
    public void onItemSelected(AdapterView<?>parent,View view,int position,long id){


                mobileno.setText(cont[position]);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // @Override
   // public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
       // return true;
   // }
    public void getPem(){
        String no=mobileno.getText().toString();
        String msg=message.getText().toString();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(no, null, msg, pi,null);

        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();


    }
}