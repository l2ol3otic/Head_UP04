package com.thesis.carhud.head_up04;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by l2ol3otic2 on 4/16/2015.
 */
public class Set_1FindDesLocationFromName extends Activity {
    Button addressButton;
    TextView addressTV;
    TextView latLongTV;
    TextView MylatlongTV;
    public String address;
    GPSTracker gps;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findloaction);


        addressTV = (TextView) findViewById(R.id.addressTV);
        latLongTV = (TextView) findViewById(R.id.latLongTV);
        MylatlongTV = (TextView)findViewById(R.id.MylatLongTV);

        addressButton = (Button) findViewById(R.id.addressButton);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                EditText editText = (EditText) findViewById(R.id.addressET);
                address = editText.getText().toString();

                Set_1GeocodingLocation locationAddress = new Set_1GeocodingLocation();
                locationAddress.getAddressFromLocation(address, getApplicationContext(), new GeocoderHandler());


            }
        });

    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    //Log.d("Check Location ADD", sharedpreferences.getString("LocationDes", ""));
                    break;
                default:
                    locationAddress = null;
            }
            sharedpreferences = getSharedPreferences("Location", Context.MODE_PRIVATE);
            latLongTV.setText("ตำแหน่งของท่าน\n" +  "Latitude : " + sharedpreferences.getString("LocationDesLongitude","Null")+"\nLongitude : "+ sharedpreferences.getString("LocationDesLongitude","Null"));
            gps = new GPSTracker(Set_1FindDesLocationFromName.this);
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                //เรียกใช้งานเมธอด getAddress
                MylatlongTV.setText("ตำแหน่งของท่าน\n" +  "Latitude : " + latitude +"\nLongitude : "+ longitude);

            }else{
                MylatlongTV.setText("อุปกรณ์์ของคุณ ปิด GPS");
            }
            Intent intent = new Intent(Set_1FindDesLocationFromName.this, DirectionActivity1.class);
            startActivity(intent);
        }
    }
}

