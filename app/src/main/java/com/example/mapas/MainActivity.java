package com.example.mapas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity
         extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener{
    GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa= googleMap;
        //personalizar el mapa
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);

        /*CameraUpdate camUpd1 = CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.0125, -79.4694), 18);
        mapa.moveCamera(camUpd1);*/
        LatLng madrid = new LatLng(-1.0125, -79.4694);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom(15)
                .bearing(180) //noreste arriba
                .tilt(30) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
        mapa.setOnMapClickListener(this);
        PolylineOptions lineas = new

                PolylineOptions()
                .add(new LatLng(45.0, -12.0))
                .add(new LatLng(45.0, 5.0))
                .add(new LatLng(34.5, 5.0))
                .add(new LatLng(34.5, -12.0))
                .add(new LatLng(45.0, -12.0));
        lineas.width(8);
        lineas.color(Color.RED);
        mapa.addPolyline(lineas);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        LatLng punto = new LatLng(latLng.latitude,
                latLng.longitude);
        mapa.addMarker(new MarkerOptions().position(latLng)
                .title("Punto"));
    }
}