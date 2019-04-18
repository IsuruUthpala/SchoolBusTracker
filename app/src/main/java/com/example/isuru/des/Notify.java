package com.example.isuru.des;

import android.*;
import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Notify extends AppCompatActivity implements AsyncResponse,LocationListener {

    private TextView distAndDurartion;
    private String tM;
    public String fom;
    public  String lt1,ln1,lt2,ln2;
    private LocationManager locationManager;
    private  static  final int rpflr=0;
    private Button btnx;
    private Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        distAndDurartion=(TextView)findViewById(R.id.distance);
        btnx=(Button)findViewById(R.id.button12);

        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fom!=null){
                    String str = fom;

                    String[] park = str.split("\\s+");
                    lt1 = park[0];
                    ln1 = park[1];




                }
                else {
                    lt1="7.0170693";
                    ln1="81.0612159";


                }
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

                    if(ContextCompat.checkSelfPermission(getApplicationContext(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED){
                        getLocation();
                    }
                    else{
                        if(shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)){

                            Toast.makeText(getApplicationContext(),"App required to access Location",Toast.LENGTH_SHORT).show();
                        }
                        requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},rpflr);
                    }
                }else{
                    getLocation();
                }









            }
        });

       //

        LocationGet asyncTask =new LocationGet(this);

        asyncTask.delegate = this;

        //execute the async task
        asyncTask.execute();




       // Toast.makeText(this,fom,Toast.LENGTH_SHORT).show();
       // new GetDistanceAsyncTask(Double.valueOf(lt1),Double.valueOf(ln1),6.9813882,81.0809846);

    }

    public void startService(View view){
        Intent intent=new Intent();
        PendingIntent pIntent=PendingIntent.getActivity(Notify.this,0,intent,0);
        Notification noti=new Notification.Builder(Notify.this).setTicker("TickerTitle").setContentTitle("Sethmi School Service")
                .setContentText("You will receive notifications once bus is near!").setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pIntent).getNotification();
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,noti);


        Intent intent1=new Intent(this,MyService.class);
        startService(intent1);



    }
    public void stopService(View view){
        Intent intent=new Intent(this,MyService.class);
        stopService(intent);



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==rpflr){
            if(grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"App will not run without location permission",Toast.LENGTH_SHORT);

            }

        }

    }

    void  getLocation(){
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }


    }









    public void btnnotify(View view){





        //Toast.makeText(this,fom,Toast.LENGTH_SHORT).show();
       // Toast.makeText(this,lt2+ln2,Toast.LENGTH_SHORT).show();
                new GetDistanceAsyncTask(Double.valueOf(lt1),Double.valueOf(ln1),Double.valueOf(lt2),Double.valueOf(ln2));





    }

    @Override
    public void processFinish(String output) {
        try {
            fom=output;



            //Here you will receive the result fired from async class
            //of onPostExecute(result) method.
        } catch (Exception e) {
            Toast.makeText(this,"Error Try again!",Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onLocationChanged(Location location) {

       // LocationText.setText("Lattitude :"+location.getLatitude()+"\nLongitude :"+location.getLongitude());
        // longi= String.valueOf(location.getLongitude());
       // lati1.setText(String.valueOf(location.getLatitude()));
      //  longi1.setText(String.valueOf(location.getLongitude()));
        lt2=String.valueOf(location.getLatitude());
        ln2=String.valueOf(location.getLongitude());
        //Toast.makeText(this,lt1 +" "+ln1,Toast.LENGTH_SHORT).show();
       // Toast.makeText(this,lt2 +" "+ln2,Toast.LENGTH_SHORT).show();
        new GetDistanceAsyncTask(Double.valueOf(lt1),Double.valueOf(ln1),Double.valueOf(lt2),Double.valueOf(ln2));




    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(Notify.this,"Please enable GPS and internet",Toast.LENGTH_SHORT).show();

    }


    public class GetDistanceAsyncTask extends AsyncTask<Void,Void,String>{
        double lat1;
        double lng1;
        double lat2;
        double lng2;
        public GetDistanceAsyncTask(double lat1,double lng1,double lat2,double lng2){

            this.lat1=lat1;
            this.lat2=lat2;
            this.lng1=lng1;
            this.lng2=lng2;
            this.execute();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... params) {
            String distance="";
            System.out.println(lat1+""+lng1+""+lat2+""+lng2);
            String url="http://maps.google.com/maps/api/directions/xml?origin="+ lat1 + "," + lng1 + "&destination=" +lat2+ "," + lng2 + "&sensor=false&units=metric";
            String tag[]={"text"};
            URL url1=null;
            try{
                url1=new URL(url);
                HttpURLConnection connection=null;
                connection=(HttpURLConnection)url1.openConnection();
                connection.setReadTimeout(30000);
                connection.setConnectTimeout(30000);
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.connect();

                InputStream is=connection.getInputStream();
                DocumentBuilder builder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc=builder.parse(is);
                if(doc!=null){
                    NodeList nl;
                    ArrayList args=new ArrayList();
                    for(String s :tag){
                        nl=doc.getElementsByTagName(s);
                        if(nl.getLength()>0){
                            Node node=nl.item(nl.getLength()-1);
                            Node node1=nl.item(nl.getLength()-2);
                            args.add(node.getTextContent());
                            args.add(node1.getTextContent());


                        }
                        else {
                            args.add(" - ");

                        }

                    }
                    distance=String.format("%s",args.get(0));
                    tM=String.format("%s",args.get(1));

                }
                else{

                    System.out.print("Doc is null");
                }




            }
            catch(Exception e){

                e.printStackTrace();
            }



            return distance;
        }


        protected void onPostExecute(String result) {
           if(result !=null){
               distAndDurartion.setText(result+ "\n"+tM);





           }
        }
    }



}
