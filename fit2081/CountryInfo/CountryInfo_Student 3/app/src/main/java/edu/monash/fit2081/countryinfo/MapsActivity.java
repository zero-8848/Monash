package edu.monash.fit2081.countryinfo;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
//import android.support.v4.app.FragmentActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Geocoder geocoder;

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getSupportActionBar().setTitle(R.string.title_activity_maps);

        geocoder = new Geocoder(this, Locale.getDefault());

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        LatLng melbourne = new LatLng(-37.814, 144.96332);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(melbourne));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                //save current location
                String msg;
                boolean actionFlag;
                String selectedCountry = "";


                List<Address> addresses = new ArrayList<>();
                try {
                    //The results of getFromLocation are a best guess and are not guaranteed to be meaningful or correct.
                    // It may be useful to call this method from a thread separate from your primary UI thread.
                    addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1); //last param means only return the first address object
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                if (addresses.size() == 0) {
                    msg = "No Country at this location!! Sorry";
                    actionFlag = false;
                }
                else {
                    android.location.Address address = addresses.get(0);
                    selectedCountry = address.getCountryName();
                    msg = "Do you want more details about " + address.getCountryName() + "?";
                    actionFlag = true;
                }

                Snackbar.make(mapFragment.getView(), msg, Snackbar.LENGTH_LONG).setAction("Details", (actionFlag) ? (new ActionOnClickListener(selectedCountry)) : null).show();
            }
        });
    }

    //Custom onclicklistener to accept 'selectedcountry' as parameter
    public class ActionOnClickListener implements View.OnClickListener {

        String country;

        public ActionOnClickListener(String country) {
            this.country = country; //this refers to the nested class's instance not the an instance of the enclosing class
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mapFragment.getContext(), CountryDetails.class);
            intent.putExtra("country", country);
            startActivity(intent);
        }
    }
}