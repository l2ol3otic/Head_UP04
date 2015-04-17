package com.thesis.carhud.head_up04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSimpleDirection = (Button)findViewById(R.id.bt41);
        buttonSimpleDirection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleDirection.class);
                startActivity(intent);
            }
        });
        Button buttonSimpleDirection42 = (Button)findViewById(R.id.bt42);
        buttonSimpleDirection42.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DirectionActivity1.class);
                startActivity(intent);
            }
        });
        Button buttonSimpleDirection43 = (Button)findViewById(R.id.bt43);
        buttonSimpleDirection43.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Set_1FindDesLocationFromName.class);
                startActivity(intent);
            }
        });
        Button buttonSimpleDirection44 = (Button)findViewById(R.id.bt44);
        buttonSimpleDirection44.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, GetMyLocation.class);
                //startActivity(intent);
                TextView t1 = (TextView)findViewById(R.id.testlalo);
                gps = new GPSTracker(MainActivity.this);
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    //เรียกใช้งานเมธอด getAddress
                    t1.setText("ตำแหน่งของคุณคือ  \nLat: " + latitude + " Long: " + longitude);

                }else{
                    t1.setText("อุปกรณ์์ของคุณ ปิด GPS");
                }
            }
        });
        Button buttonSimpleDirection45 = (Button)findViewById(R.id.bt45);
        buttonSimpleDirection45.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Set_1Map_Maprender.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
