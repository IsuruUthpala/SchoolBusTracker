package com.example.isuru.des;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Main5Activity extends AppCompatActivity implements OnMapReadyCallback,AsyncResponse{


    public String fom;
    public String lt,ln;


    //this override the implemented method from asyncTask

    GoogleMap mGoogleMap;
    //public  String tempo="no";
    TextView tst;
    private Button btnapi;
    public void btnapimethod(View view){
        try {
            LocationGet asyncTask =new LocationGet(this);

            asyncTask.delegate = this;

            //execute the async task
            asyncTask.execute();
            initMap();
            // Toast.makeText(this,fom,Toast.LENGTH_SHORT).show();
            //  Toast.makeText(this,fom,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"Error Try again!",Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //LocationGet asyncTask =new LocationGet(this);

       // asyncTask.delegate = this;

        //execute the async task
      //  asyncTask.execute();

        //tst=(TextView)findViewById(R.id.textView3);
        //  LocationGet locationGet=new LocationGet(this);
        // String typ="";
        //String a="";
        // String b="";
        //String c="";
        // locationGet.execute();






        if(googleServicesAvailable()){

            Toast.makeText(this,"Refresh Until You get Location!",Toast.LENGTH_SHORT).show();

            setContentView(R.layout.activity_main5);
            initMap();
        }
        else{

            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void processFinish(String output){
        try {
            fom=output;


            //Here you will receive the result fired from async class
            //of onPostExecute(result) method.
        } catch (Exception e) {
            Toast.makeText(this,"Error Try again!",Toast.LENGTH_SHORT).show();
        }
    }

    private void initMap() {
        try {
            MapFragment mapFragment =(MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
            mapFragment.getMapAsync(this);
        } catch (Exception e) {
            Toast.makeText(this,"Error Try again!",Toast.LENGTH_SHORT).show();
        }
    }


    public boolean googleServicesAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable=api.isGooglePlayServicesAvailable(this);
        if(isAvailable== ConnectionResult.SUCCESS){
            return true;


        }
        else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this,isAvailable,0);
            dialog.show();


        }

        else {
            Toast.makeText(this,"Can't connect to play services",Toast.LENGTH_SHORT).show();

        }
        return false;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            if(fom!=null){
                String str = fom;

                String[] park = str.split("\\s+");
                lt = park[0];
                ln = park[1];




            }
            else {
                lt="6.9888338";
                ln="81.0415076";


            }


            //Toast.makeText(this,park[0],Toast.LENGTH_SHORT).show();
            //Toast.makeText(this,park[1],Toast.LENGTH_SHORT).show();

            mGoogleMap=googleMap;
            double lat=Double.parseDouble(lt);
            double lng=Double.parseDouble(ln);
            //double lat=6.9813882;

            //double lng= 81.0809846;
            goToLocation(lat,lng,18);
            MarkerOptions options =new MarkerOptions().position(new LatLng(lat,lng));
            mGoogleMap.addMarker(options);
            Toast.makeText(this,fom,Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this,"Error Try again!",Toast.LENGTH_SHORT).show();
        }


    }

    private void goToLocation(double lat, double lng,float zoom) {
        try {
            LatLng l1=new LatLng(lat,lng);
            CameraUpdate update= CameraUpdateFactory.newLatLngZoom(l1,zoom);
            mGoogleMap.moveCamera(update);
        } catch (Exception e) {
            Toast.makeText(this,"Error Try again4!",Toast.LENGTH_SHORT).show();
        }

    }
}
