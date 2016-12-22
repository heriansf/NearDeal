package com.redcamel.heriansf.neardeal;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.redcamel.heriansf.neardeal.util.Popup;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;
    private LocationManager mlocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mengabil fragment map dari View
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Popup.showLoading(this);
        // Mengambil Googlemap dari fragment
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //set variable mMap ke GoogleMap
        mMap = googleMap;
        mlocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        // app sudah diizinkan, langsung ambil lokasi
        else {
            mlocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 4, this);
        }

        //set lokasi monas
        LatLng monasLatlng = new LatLng(-6.1753871, 106.8249641);
        MarkerOptions markerOptions = new MarkerOptions().position(monasLatlng).title ("Gunadarma").icon(BitmapDescriptorFactory.fromResource(R.drawable.store));
        mMap.addMarker(markerOptions);
        LatLng gunadarmaLatlng = new LatLng(-6.3684569, 106.8311028);
        MarkerOptions markerOptios = new MarkerOptions().position(gunadarmaLatlng).title ("Monas").icon(BitmapDescriptorFactory.fromResource(R.drawable.store));
        mMap.addMarker(markerOptios);
        //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(monasLatlng, 12f);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(monasLatlng, 12f));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // hurray diizinkan, baru minta lokasi
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                // Minta Lokasi Baru
                mlocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 4, this);
            }
            else {
                // ooow, tidak dizinkan, matikan aplikasi
                finish();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // Updtae lokasi dalam sekali saja
        mlocationManager.removeUpdates(this);
        // Lokasi user diterima
        LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(userLatLng)
                .title("My Location");

        // tambahkan marker ke map
        mMap.addMarker(markerOptions);

        // set camera update
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(userLatLng, 15);
        mMap.moveCamera(cameraUpdate);
        Popup.dismiss();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
