package com.example.isuru.des;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main8Activity extends AppCompatActivity {


    ListView lv,list_view;
    ArrayAdapter<String> adapter;
    String addr="http://trackmybusuwu.000webhostapp.com/get.php";
    InputStream is =null;
    String line=null;
    String result=null;
    JSONObject jo=null;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        lv=(ListView)findViewById(R.id.listjson);
       StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        getData();
       // listview();
       adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);


    }


    public void listview(){

        list_view=(ListView)findViewById(R.id.listjson);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.name_list,data);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=(String)list_view.getItemAtPosition(position);
                Toast.makeText(Main8Activity.this,"position"+position+" vaalue :"+value,Toast.LENGTH_LONG).show();


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
        data[i]=jo.getString("message");



    }



}catch (Exception e){

   // Toast.makeText(Main8Activity.this,"error3",Toast.LENGTH_LONG).show();
    e.printStackTrace();
}





    }
}
