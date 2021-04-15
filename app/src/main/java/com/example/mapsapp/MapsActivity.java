package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng KP_HOUSE = new LatLng(37.047291, -76.493837);
    private static final LatLng CC_NZ = new LatLng(-43.5321, 172.6362);
    private static final LatLng NZ_MT = new LatLng(-44.9083700, 167.9100500);
    private static final LatLng NZ_RT = new LatLng( -44.7283600, 168.1800600);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.addMarker(new MarkerOptions().position(KP_HOUSE).title("Marker KP"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(KP_HOUSE));

        setupSimpleSpinner();
    }
    //handle navigate button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_KP:
                goToKP();
                break;

            case R.id.action_NZ:
                goToNZ();
                break;
            case R.id.action_MT:
                goToMT();
                break;
            case R.id.action_RT:
                goToRT();
                break;

            default:
                break;
        }
        return true;
    }

    private void goToKP() {
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(KP_HOUSE, 15);
        mMap.addMarker(new MarkerOptions().position(CC_NZ).title("Keith and Lynns house"));
        mMap.animateCamera(camera);
    }

    private void goToNZ() {
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(CC_NZ, 15);
        mMap.addMarker(new MarkerOptions().position(CC_NZ).title("Christchurch NZ"));
        mMap.animateCamera(camera);
    }
    private void goToMT() {
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(NZ_MT, 15);
        mMap.addMarker(new MarkerOptions().position(NZ_MT).title("Milford Track NZ\nworlds best hike"));
        mMap.animateCamera(camera);
    }

    private void goToRT() {
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(NZ_RT, 15);
        mMap.addMarker(new MarkerOptions().position(NZ_RT).title("Routeburn Track NZ\nworlds best hike"));
        mMap.animateCamera(camera);
    }

    AdapterView.OnItemSelectedListener mySpinnerListener;
    private void setupSimpleSpinner() {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.map_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //set listener
        mySpinnerListener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    // Sets the map type
                    case 0:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case 1:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 2:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case 3:
                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                    case 4:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        break;
                    default:
                        break;
                }

            }


            /**
             * Callback method to be invoked when the selection disappears from this
             * view. The selection can disappear for instance when touch is activated
             * or when the adapter becomes empty.
             *
             * @param parent The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        //respond when spinner clicked
        spinner.setOnItemSelectedListener(mySpinnerListener);
    }


}