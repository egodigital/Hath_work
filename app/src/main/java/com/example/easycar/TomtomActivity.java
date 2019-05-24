package com.example.easycar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.MapFragment;
import com.tomtom.online.sdk.map.Marker;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TextBalloonViewAdapter;
import com.tomtom.online.sdk.map.TomtomMap;
import com.tomtom.online.sdk.map.TomtomMapCallback;

public class TomtomActivity extends AppCompatActivity implements OnMapReadyCallback,
        TomtomMapCallback.OnMapLongClickListener{
    private TomtomMap tomtomMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomtom);
        initTomTomServices();
        initUIViews();
        setupUIViewListeners();
    }

    @Override
    public void onMapReady(@NonNull TomtomMap tomtomMap) {
        this.tomtomMap = tomtomMap;
        this.tomtomMap.setMyLocationEnabled(true);
        this.tomtomMap.addOnMapLongClickListener(this);
        this.tomtomMap.getMarkerSettings().setMarkersClustering(true);

        Intent intent = getIntent();
        String tempData = intent.getStringExtra("mapstr");



        tomtomMap.getMarkerSettings().setMarkerBalloonViewAdapter(new TextBalloonViewAdapter());

        LatLng pos[]=new LatLng[5];
        pos[0]=new LatLng(50.7816102,6.0760597);//ponttor
        pos[1]=new LatLng(50.7773859,6.0872174);//bushof
        pos[2]=new LatLng(50.774117,6.0846652);//Elisenbrunnen
        pos[3]=new LatLng(50.7774025,6.0806513);//schanz
        pos[4]=new LatLng(50.7741336,6.0780991);//Theater

        LatLng position;
        for (int i=0;i<5;i++) {

            position=pos[i];

            SimpleMarkerBalloon balloon = new SimpleMarkerBalloon("Car "+i);
            MarkerBuilder markerBuilder = new MarkerBuilder(position)
                    .markerBalloon(balloon);

            Marker m = tomtomMap.addMarker(markerBuilder);
        }
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }
    private void initTomTomServices() {
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getAsyncMap(this);
    }

    private void initUIViews() {}
    private void setupUIViewListeners() {}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.tomtomMap.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
