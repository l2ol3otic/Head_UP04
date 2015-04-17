package com.thesis.carhud.head_up04;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivitys extends FragmentActivity {

    GoogleMap mMap;
    int i=0,j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mMap = fm.getMap();

        LatLng latLng = new LatLng(77,77);



        Marker mrk=mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Rohit")
                .draggable(true)
                .snippet("Delivery Boy")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mrk.remove();

        animateMarker(mrk,mrk.isVisible());

    }

    public void animateMarker(final Marker marker, final boolean hideMarker) {

        final Handler handler = new Handler();

        final double [] lnglist={75.123445,71.290909,72,73,74,75,77,78,79,82};
        final double [] latlist={71.232334,74.898899,63,64,65,66,64,62,63,63};

        handler.post(new Runnable() {
            @Override
            public void run() {


                double lng = lnglist[i++];
                double lat = latlist[j++];

                Marker mrk=mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lng))
                        .title("Rohit")
                        .draggable(true)
                        .snippet("Delivery Boy")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setCompassEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 5));

                for(int k=0;k<200000000;k++);

                if(i>=9)
                {
                    i=0;
                    j=0;

                }
                else{


                    mrk.remove();
                    handler.postDelayed(this, 0);

                }

            }
        });


    }

}
