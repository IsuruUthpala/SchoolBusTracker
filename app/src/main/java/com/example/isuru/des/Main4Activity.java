package com.example.isuru.des;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class Main4Activity extends AppCompatActivity implements LocationListener {
    private static ListView list_view;
    private static  String[] NAMES=new String[]{};

    private TextView LocationText;
    private  TextView lan1,longi1,lati1;
    private Button btn;
    private LocationManager locationManager;
    private  static  final int rpflr=0;

    public void listview(){

        list_view=(ListView)findViewById(R.id.listt);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.name_list,NAMES);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=(String)list_view.getItemAtPosition(position);
                Toast.makeText(Main4Activity.this,"position"+position+" vaalue :"+value,Toast.LENGTH_LONG).show();


            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main4);
        LocationText=(TextView)findViewById(R.id.textView2) ;
        lati1=(TextView)findViewById(R.id.textView5) ;
        longi1=(TextView)findViewById(R.id.textView6) ;
        lan1=(TextView)findViewById(R.id.textView7) ;
        btn=(Button)findViewById(R.id.btnLOC);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

                    if(ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED){
                        getLocation();
                    }
                    else{
                        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){

                            Toast.makeText(getApplicationContext(),"App required to access Location",Toast.LENGTH_SHORT).show();
                        }
                        requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},rpflr);
                    }
                }else{
                    getLocation();
                }






            }
        });



        listview();
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
    //public String longi="";
   // public String lati="";
   // public String lan="";



    @Override
    public void onLocationChanged(Location location) {
        LocationText.setText("Lattitude :"+location.getLatitude()+"\nLongitude :"+location.getLongitude());
       // longi= String.valueOf(location.getLongitude());
        lati1.setText(String.valueOf(location.getLatitude()));
        longi1.setText(String.valueOf(location.getLongitude()));

        try{
            Geocoder geocoder=new Geocoder(this, Locale.getDefault());
            List<Address> addresses =geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            LocationText.setText(LocationText.getText()+"\n"+addresses.get(0).getAddressLine(0));
//lan=addresses.get(0).getAddressLine(0);
lan1.setText(addresses.get(0).getAddressLine(0));

        }
        catch (Exception e){
            e.printStackTrace();
        }
        sendLoc();

    }




    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(Main4Activity.this,"Please enable GPS and internet",Toast.LENGTH_SHORT).show();

    }
    public void sendLoc(){
        Main4Activity mk=new Main4Activity();




        String lane= String.valueOf(lan1.getText());
        String longitude= String.valueOf(longi1.getText());
        String latitude=String.valueOf(lati1.getText());
        String type="location";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);

        backgroundWorker.execute(type,latitude,longitude,lane);



    }
}
