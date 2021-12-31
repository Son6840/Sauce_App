package com.daelim.sauce.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.MenuItem;

import com.daelim.sauce.R;
import com.daelim.sauce.fragment.F_couponAct;
import com.daelim.sauce.fragment.F_mainAct;
import com.daelim.sauce.fragment.F_mapAct;
import com.daelim.sauce.fragment.F_mypageAct;
import com.daelim.sauce.fragment.storeList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//손
public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰0

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      checkDangerousPermissions();

      



        bottomNavigationView = findViewById(R.id.bottomNavi);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame,new F_mainAct()).commit();//첫 프레그먼트 화면세팅
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_mainAct()).commit();
                        break;
                    case R.id.action_map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_mapAct()).commit();

                        break;
                    case R.id.action_coupon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_couponAct()).commit();
                        break;
                    case R.id.action_mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new F_mypageAct()).commit();
                        break;
                }
                return true;
            }
        });


        }
    public  void switchCategory(String category_index) {
        switch (category_index) {
            case "치킨":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();

                break;

            case "피자":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();

                break;

            case "햄버거":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();
                break;

            case "한식":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();
                break;

            case "일식":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();
                break;

            case "타코":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();
                break;

            case "야식":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();
                break;

            case "카페":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();
                break;

            case "디저트":
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();
                break;

            case "간편식":
               getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();
                break;

        }
    }

//    private void checkDangerousPermissions() {
//        String[] permissions = {
//                android.Manifest.permission.ACCESS_COARSE_LOCATION,
//                android.Manifest.permission.ACCESS_FINE_LOCATION,
//
//        };
//
//        int permissionCheck = PackageManager.PERMISSION_GRANTED;
//        for (int i = 0; i < permissions.length; i++) {
//            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
//            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
//                break;
//            }
//        }
//
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
//                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
//            } else {
//                ActivityCompat.requestPermissions(this, permissions, 1);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {
//            for (int i = 0; i < permissions.length; i++) {
//                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//
//    }
//    //------------------권한 설정 끝-----------------------

}

