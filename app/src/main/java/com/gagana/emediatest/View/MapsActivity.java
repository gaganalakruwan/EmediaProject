package com.gagana.emediatest.View;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gagana.emediatest.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gagana.emediatest.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Double lat, lang;
    String title, address;

    ImageView location;
    ImageView back;
    TextView headerTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        lat = Double.parseDouble(intent.getStringExtra("lat").toString());
        lang = Double.parseDouble(intent.getStringExtra("lang").toString());
        title = intent.getStringExtra("title");
        address = intent.getStringExtra("address");

        location = (ImageView) findViewById(R.id.main_page_toolbar).findViewById(R.id.iconLocation);
        back = (ImageView) findViewById(R.id.main_page_toolbar).findViewById(R.id.iconBack);
        headerTitle=(TextView) findViewById(R.id.main_page_toolbar).findViewById(R.id.title);

        headerTitle.setText("Map");

        location.setVisibility(View.INVISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1=new Intent(MapsActivity.this,SingleDataViewActivity.class);
//                startActivity(intent1);
                finish();

            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        /**
         * When click on the marker then show the description about place
         */
        LatLng sydney = new LatLng(lat, lang);
        mMap.addMarker(new MarkerOptions().position(sydney).title(address));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}