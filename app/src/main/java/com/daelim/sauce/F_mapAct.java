package com.daelim.sauce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class F_mapAct extends Fragment implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener{
    private View view;
    private MapView googlemap = null;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_map, container, false);
        //MapView 호출
        googlemap = (MapView) view.findViewById(R.id.map);
        googlemap.onCreate(savedInstanceState);
        googlemap.getMapAsync(this);

        return view;
    }
    //이 메서드가 없으면 지도 출력 x
    @Override
    public void onStart() {
        super.onStart();
        googlemap.onStart();
    }

    @Override
    public void onStop () {
        super.onStop();
        googlemap.onStop();

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

    //맵뷰 설정
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //마커찍기(위도,경도)
        LatLng Daelim = new LatLng(37.40387620799947, 126.93031303076037);

        //마커 옵션
        MarkerOptions marker = new MarkerOptions();
        marker.position(Daelim); //마커 위치
        marker.title("대림대학교");
        marker.snippet("경기도 안양시 동안구 비산1동 임곡로 대림대학교");

        //맵에 마커표시, 인포윈도우 보여줌
        googleMap.addMarker(marker).showInfoWindow();

        //인포윈도우 클릭
        googleMap.setOnInfoWindowClickListener(this);

        //맵뷰 카메라위치, 줌 설정
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Daelim, 13));
    }

    //인포윈도우 클릭 리스너
    @Override
    public void onInfoWindowClick (Marker marker){

    }

}


