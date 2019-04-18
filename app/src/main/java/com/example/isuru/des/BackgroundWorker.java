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
 * Created by Isuru on 5/2/2018.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    public  String temp="no";

    BackgroundWorker(Context ctx){
        context=ctx;

    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url="http://trackmybusuwu.000webhostapp.com/login.php";
        String location_url="http://trackmybusuwu.000webhostapp.com/location.php";
        String feedback_url="http://trackmybusuwu.000webhostapp.com/feed.php";
        if(type.equals("Driver")){
            try {
                String user_name=params[1];
                String password=params[2];

                URL url =new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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

       else if(type.equals("feedback")){
            try {
                String msg=params[1];
               // String password=params[2];

                URL url =new URL(feedback_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("msg","UTF-8")+"="+URLEncoder.encode(msg,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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

        else if(type.equals("location")){

            try {

                String latitude=params[1];
                String longitude=params[2];
                String lane=params[3];

                URL url =new URL(location_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("Latitude","UTF-8")+"="+URLEncoder.encode(latitude,"UTF-8")+"&"+URLEncoder.encode("Longitude","UTF-8")+"="+URLEncoder.encode(longitude,"UTF-8")+"&"+URLEncoder.encode("Lane","UTF-8")+"="+URLEncoder.encode(lane,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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
     //  alertDialog=new AlertDialog.Builder(context).create();
     //  alertDialog.setTitle("Status");

    }

    @Override
    protected void onPostExecute(String result) {
       // alertDialog.setMessage(result);
try {
    if (result.equals("Login Unsuccessful!")) {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage("Login Unsuccessful!");

        alertDialog.show();
    }
    else if(result.equals("Location Updated!")){
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage("Location Updated!");

        alertDialog.show();

    }

    else if(result.equals("driver")){

        method1();


    }
    else if(result.equals("parent")){

        method2();


    }
    else if(result.equals("feedback sent!")){

        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage("Feedback sent!");

        alertDialog.show();


    }
    else if(result.equals("feedback fail!")){

        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage("Feedback fail!");

        alertDialog.show();


    }

    else{
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage("Error Try again");

        alertDialog.show();

    }


}catch (Exception e){
    alertDialog=new AlertDialog.Builder(context).create();
    alertDialog.setTitle("Status");
    alertDialog.setMessage("Connect to Internet!");

    alertDialog.show();
}

    }

    private void method2() {
        Intent intent = new Intent(context, Main6Activity.class);
        context.startActivity(intent);
    }

    public void method1() {
        Intent intent = new Intent(context, Main3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
