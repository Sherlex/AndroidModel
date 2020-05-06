package com.example.eco;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap googleMaps;
    SupportMapFragment mapFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);

        createMapView();
        addMarker();
    }

    private void addMarker () {
        if (googleMaps != null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(59.982806, 30.356325));
            markerOptions.title("Экополис");
            googleMaps.addMarker(markerOptions);

            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions.position(new LatLng(0, 0));
            markerOptions.title("Marker");
            googleMaps.addMarker(markerOptions);

            MarkerOptions markerOptions2 = new MarkerOptions();
            markerOptions.position(new LatLng(0, 0));
            markerOptions.title("Marker");
            googleMaps.addMarker(markerOptions);

        }
    }

    private void createMapView() {
        try {
            //if(googleMaps == null) {
                 mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
                mapFragment.getMapAsync(this);

                if (null == googleMaps) {
                    Toast.makeText(getApplicationContext(), "Ошибка инициализации карты!", Toast.LENGTH_SHORT).show();
                }
           // }
        }catch (NullPointerException exception) {
            Log.e("TAG", exception.toString());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMaps = googleMap;

    }
}
