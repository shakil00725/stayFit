package com.androidhive.googleplacesandmaps;

import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class PlacesMapActivity extends Activity
{
	 
    // Google Map
    private GoogleMap googleMap;
    PlacesList nearPlaces;
    List<Overlay> mapOverlays;
    AddItemizedOverlay itemizedOverlay;

	GeoPoint geoPoint;
	// Map controllers
	MapController mc;
	
	double latitude;
	double longitude;
	OverlayItem overlayitem;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_places);
 
        try {
            // Loading map
            initilizeMap();
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            googleMap.getUiSettings().setRotateGesturesEnabled(true);
         // Getting intent data
    		Intent i = getIntent();
    		
    		// Users current geo location
    		String user_latitude = i.getStringExtra("user_latitude");
    		String user_longitude = i.getStringExtra("user_longitude");
    		
    		// Nearplaces list
    		nearPlaces = (PlacesList) i.getSerializableExtra("near_places");
    	 final LatLng pos = new LatLng(Double.parseDouble(user_latitude),Double.parseDouble(user_longitude));
    	 Marker mypos = googleMap.addMarker(new MarkerOptions()
             .position(pos)
             .title("YOur location")
             .snippet("That is You")
             .icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_red)));
//    		MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(user_latitude), Double.parseDouble(user_longitude))).title("");
    	
    	 googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {
    	        public void onCameraChange(CameraPosition arg0) {
    	            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 13));
    	        }
    	    });
    	 
    	 if (nearPlaces.results != null){
    		 
    		 final LatLngBounds.Builder builder = new LatLngBounds.Builder();
    		 for (Place place : nearPlaces.results){
    			 latitude = place.geometry.location.lat; // latitude
 				longitude = place.geometry.location.lng; // longitude
 				//String NAMEplace.name
 				final LatLng nearpos = new LatLng(latitude,longitude);
 				 builder.include(nearpos);
 				googleMap.addMarker(new MarkerOptions()
 				                        .position(nearpos)
 				                        .title(place.name)
 				                        .snippet(place.vicinity)
 				                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_blue)));
    		 }
    		 
    		 googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {
                 
                 public void onCameraChange(CameraPosition arg0) {
                     googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),50));
                     googleMap.setOnCameraChangeListener(null);
                 }
             });
    		 
    	 }
    	 
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
         googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
 
}