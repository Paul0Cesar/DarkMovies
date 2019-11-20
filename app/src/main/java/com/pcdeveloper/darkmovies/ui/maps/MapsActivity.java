package com.pcdeveloper.darkmovies.ui.maps;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Countrie;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.data.network.webObjects.IsoMap;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent i=getIntent();
        if(i.hasExtra("movie")){
            String mv=i.getStringExtra("movie");
            Movie e=new Gson().fromJson(mv, Movie.class);
            if(e!=null && e.getCountries()!=null && e.getCountries().size()>1){
                Toast.makeText(this,"Existe mais de um pais de gravação,por favor olhe o mapa!!",Toast.LENGTH_LONG).show();
            }
            for(final Countrie c:e.getCountries()){
                dataManager.getCoordToMap(c.getIso(), new CallBackto<IsoMap>() {
                    @Override
                    public void isRefreshing(Boolean refreshing) {

                    }

                    @Override
                    public void onErro(String err, Throwable throwable) {

                    }

                    @Override
                    public void result(IsoMap result) {
                       if(result!=null && result.getLatlng()!=null){
                           LatLng latLng = new LatLng(result.getLatlng()[0], result.getLatlng()[1]);
                           addMarkerOnMAp(latLng,c.getName());
                       }

                    }
                });
            }
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void addMarkerOnMAp( LatLng latLng,String name){
        mMap.addMarker(new MarkerOptions().position(latLng).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
