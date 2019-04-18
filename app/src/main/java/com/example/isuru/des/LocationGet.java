package com.example.isuru.des;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by Isuru on 5/31/2018.
 */

public class LocationGet extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    public AsyncResponse delegate = null;


    LocationGet(Context ctx){
        context=ctx;

    }



    @Override
    public String doInBackground(String... params) {
        String type="Driver";

        String location_url="http://trackmybusuwu.000webhostapp.com/getloc.php";
        if(type.equals("Driver")){
            try {

                URL url =new URL(location_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        //alertDialog=new AlertDialog.Builder(context).create();
       // alertDialog.setTitle("Status");

    }



    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);


       // alertDialog.setMessage(result);


       // alertDialog.show();


    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
