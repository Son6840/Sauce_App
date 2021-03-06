package com.daelim.sauce.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.daelim.sauce.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class F_mapAct extends Fragment implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener, ActivityCompat.OnRequestPermissionsResultCallback{

        private View view;
        private MapView googlemap = null;

        private GoogleMap mMap;
        private Marker currentMarker = null;

        private static final String TAG = "googlemap_example";
        private static final int GPS_ENABLE_REQUEST_CODE = 2001;
        private static final int UPDATE_INTERVAL_MS = 1000;  // 1???
        private static final int FASTEST_UPDATE_INTERVAL_MS = 500; // 0.5???

        // onRequestPermissionsResult?????? ????????? ???????????? ActivityCompat.requestPermissions??? ????????? ????????? ????????? ???????????? ?????? ??????
        private static final int PERMISSIONS_REQUEST_CODE = 100;
        boolean needRequest = false;

        // ?????? ???????????? ?????? ????????? ???????????? ??????
        String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};  // ?????? ?????????

        Location mCurrentLocatiion;
        LatLng currentPosition;
        
        private FusedLocationProviderClient mFusedLocationClient;
        private LocationRequest locationRequest;
        private Location location;


        private void setLocationRequest(){
                locationRequest = new LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(UPDATE_INTERVAL_MS)
                        .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

                LocationSettingsRequest.Builder builder =
                        new LocationSettingsRequest.Builder();

                builder.addLocationRequest(locationRequest);
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_map, container, false);
        setLocationRequest();

        //MapView ??????
        googlemap = (MapView) view.findViewById(R.id.map);
        googlemap.onCreate(savedInstanceState);
        googlemap.getMapAsync(this);

        return view;
        }
        //??? ???????????? ????????? ?????? ?????? x

        @Override
        public void onStart() {
        super.onStart();
        googlemap.onStart();
        Log.d(TAG, "onStart");

        if (checkPermission()) {

                Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

                if (mMap!=null)
                        mMap.setMyLocationEnabled(true);

        }
        }

        @Override
        public void onStop () {
        super.onStop();
        googlemap.onStop();
        if (mFusedLocationClient != null) {

                Log.d(TAG, "onStop : call stopLocationUpdates");
                mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
        }

        public String getCurrentAddress(LatLng latlng) {

                //????????????... GPS??? ????????? ??????
                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

                List<Address> addresses;

                try {

                        addresses = geocoder.getFromLocation(
                                latlng.latitude,
                                latlng.longitude,
                                1);
                } catch (IOException ioException) {
                        //???????????? ??????
                        Toast.makeText(getActivity(), "???????????? ????????? ????????????", Toast.LENGTH_LONG).show();
                        return "???????????? ????????? ????????????";
                } catch (IllegalArgumentException illegalArgumentException) {
                        Toast.makeText(getActivity(), "????????? GPS ??????", Toast.LENGTH_LONG).show();
                        return "????????? GPS ??????";

                }


                if (addresses == null || addresses.size() == 0) {
                        Toast.makeText(getActivity(), "?????? ?????????", Toast.LENGTH_LONG).show();
                        return "?????? ?????????";

                } else {
                        Address address = addresses.get(0);
                        return address.getAddressLine(0).toString();
                }

        }

        public boolean checkLocationServicesStatus() {
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

                return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                        || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

        public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {


                if (currentMarker != null) currentMarker.remove();


                LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(currentLatLng);
                markerOptions.title(markerTitle);
                markerOptions.snippet(markerSnippet);
                markerOptions.draggable(true);


                currentMarker = mMap.addMarker(markerOptions);

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
                mMap.moveCamera(cameraUpdate);

        }


        @Override
        public void onSaveInstanceState (@Nullable Bundle outState){
        super.onSaveInstanceState(outState);
        googlemap.onSaveInstanceState(outState);
        }

        @Override
        public void onResume() {
        super.onResume();
        googlemap.onResume();
}


        @Override
        public void onDestroy() {
        super.onDestroy();
        googlemap.onLowMemory();
        }

        //?????? ??????
        @Override
        public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: ");
        mMap = googleMap;
        setDefaultLocation(googleMap);

        //????????? ????????? ??????
        // 1. ?????? ???????????? ????????? ????????? ??????
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {

                // 2. ?????? ???????????? ????????? ?????????
                // ( ??????????????? 6.0 ?????? ????????? ????????? ???????????? ???????????? ????????? ?????? ????????? ?????? ??????.)


                startLocationUpdates(); // 3. ?????? ???????????? ??????


        }else {  //2. ????????? ????????? ????????? ?????? ????????? ????????? ????????? ??????

                // 3-1. ???????????? ????????? ????????? ??? ?????? ?????? ????????????
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {

                        // 3-2. ????????? ???????????? ?????? ?????????????????? ???????????? ????????? ????????? ????????????
                        Snackbar.make(view,"??? ?????? ??????????????? ?????? ?????? ????????? ???????????????.",
                                Snackbar.LENGTH_INDEFINITE).setAction("??????", new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {

                                        // 3-3. ??????????????? ????????? ????????? ?????????. ?????? ????????? onRequestPermissionResult?????? ??????
                                        ActivityCompat.requestPermissions( getActivity(), REQUIRED_PERMISSIONS,
                                                PERMISSIONS_REQUEST_CODE);
                                }
                        }).show();


                } else {
                        // 4-1. ???????????? ????????? ????????? ??? ?????? ?????? ???????????? ????????? ??????
                        // ?????? ????????? onRequestPermissionResult?????? ??????
                        ActivityCompat.requestPermissions( getActivity(), REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                }

        }
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                @Override
                public void onMapClick(LatLng latLng) {

                        Log.d( TAG, "onMapClick :");
                }
        });
        }
        LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);

                        List<Location> locationList = locationResult.getLocations();

                        if (locationList.size() > 0) {
                                location = locationList.get(locationList.size() - 1);
                                //location = locationList.get(0);

                                currentPosition
                                        = new LatLng(location.getLatitude(), location.getLongitude());


                                String markerTitle = getCurrentAddress(currentPosition);
                                String markerSnippet = "??????:" + String.valueOf(location.getLatitude())
                                        + " ??????:" + String.valueOf(location.getLongitude());

                                Log.d(TAG, "onLocationResult : " + markerSnippet);


                                //?????? ????????? ?????? ???????????? ??????
                                setCurrentLocation(location, markerTitle, markerSnippet);

                                mCurrentLocatiion = location;
                        }
                }
        };
        private void startLocationUpdates() {

                if (!checkLocationServicesStatus()) {

                        Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
                        showDialogForLocationServiceSetting();
                }else {

                        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.ACCESS_FINE_LOCATION);
                        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.ACCESS_COARSE_LOCATION);



                        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                                hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED   ) {

                                Log.d(TAG, "startLocationUpdates : ????????? ???????????? ??????");
                                return;
                        }


                        Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates");

                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

                        if (checkPermission())
                                mMap.setMyLocationEnabled(true);

                }

        }


public void setDefaultLocation(GoogleMap googleMap){
        //????????????(??????,??????)
        LatLng DEFAULT_LOCATION = new LatLng(37.40387620799947, 126.93031303076037);

        //?????? ??????

        String markerTitle = "???????????? ????????? ??? ??????";
        String markerSnippet = "??????????????? gps ???????????? ??????";

        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = googleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        googleMap.moveCamera(cameraUpdate);

        }
        //??????????????? ????????? ????????? ????????? ?????? ????????????
        private boolean checkPermission() {

                int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION);
                int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION);



                if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                        hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {
                        return true;
                }

                return false;

        }



        /*
         * ActivityCompat.requestPermissions??? ????????? ????????? ????????? ????????? ???????????? ?????????
         */
        @Override
        public void onRequestPermissionsResult(int permsRequestCode,
                                               @NonNull String[] permissions,
                                               @NonNull int[] grandResults) {

                if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

                        // ?????? ????????? PERMISSIONS_REQUEST_CODE ??????, ????????? ????????? ???????????? ??????????????????

                        boolean check_result = true;


                        // ?????? ???????????? ??????????????? ??????

                        for (int result : grandResults) {
                                if (result != PackageManager.PERMISSION_GRANTED) {
                                        check_result = false;
                                        break;
                                }
                        }


                        if ( check_result ) {

                                // ???????????? ??????????????? ?????? ??????????????? ??????
                                startLocationUpdates();
                        }
                        else {
                                // ????????? ???????????? ????????? ?????? ????????? ??? ?????? ????????? ??????????????? ?????? ??????

                                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])
                                        || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[1])) {


                                        // ???????????? ????????? ????????? ???????????? ?????? ?????? ???????????? ????????? ???????????? ?????? ????????? ??? ??????
                                        Snackbar.make(view, "???????????? ?????????????????????. ?????? ?????? ???????????? ???????????? ??????????????????. ",
                                                Snackbar.LENGTH_INDEFINITE).setAction("??????", new View.OnClickListener() {

                                                @Override
                                                public void onClick(View view) {

                                                        getActivity().finish();
                                                }
                                        }).show();

                                }else {


                                        // "?????? ?????? ??????"??? ???????????? ???????????? ????????? ????????? ???????????? ??????(??? ??????)?????? ???????????? ???????????? ?????? ????????? ??? ??????
                                        Snackbar.make(view, "???????????? ?????????????????????. ??????(??? ??????)?????? ???????????? ???????????? ?????????. ",
                                                Snackbar.LENGTH_INDEFINITE).setAction("??????", new View.OnClickListener() {

                                                @Override
                                                public void onClick(View view) {

                                                        getActivity().finish();
                                                }
                                        }).show();
                                }
                        }

                }
        }
        //??????????????? GPS ???????????? ?????? ????????????
        private void showDialogForLocationServiceSetting() {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("?????? ????????? ????????????");
                builder.setMessage("?????? ???????????? ???????????? ?????? ???????????? ???????????????.\n"
                        + "?????? ????????? ???????????????????");
                builder.setCancelable(true);
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent
                                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
                        }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                        }
                });
                builder.create().show();
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                switch (requestCode) {

                        case GPS_ENABLE_REQUEST_CODE:

                                //???????????? GPS ?????? ???????????? ??????
                                if (checkLocationServicesStatus()) {
                                        if (checkLocationServicesStatus()) {

                                                Log.d(TAG, "onActivityResult : GPS ????????? ?????????");


                                                needRequest = true;

                                                return;
                                        }
                                }

                                break;
                }
        }

        //??????????????? ?????? ?????????
        @Override
        public void onInfoWindowClick (Marker marker){

        }


}
