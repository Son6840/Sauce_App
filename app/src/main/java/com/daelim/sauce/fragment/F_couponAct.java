package com.daelim.sauce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daelim.sauce.R;

public class F_couponAct extends Fragment {
    private View view;

    @Nullable

    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.frag_coupon,container,false);
        return view;
//        barcodeScannerView.decodeContinuous(new BarcodeCallback() {
//            @Override
//            public void barcodeResult(BarcodeResult result) {
//                try {
//                    String result_data = "";
//                    if(captureFlag == false){ //TODO [최초 1번 스캔된 경우]
//                        result_data = String.valueOf(result.toString());
//                        byte result_byte [];
//                        //TODO Arrays.toString 형식 바이트 문자열 [104,101,108,108,111]
//                        if(result_data.contains("[") && result_data.contains("]")
//                                && result_data.contains(",")){ //TODO [바이트 값 형식 문자열인 경우 > 한글로 출력]
//                            result_byte = getByteArray(result_data);
//                            result_data = new String(result_byte, "utf-8");
//                        }
//                        Log.d("---","---");
//                        Log.w("//===========//","================================================");
//                        Log.d("","\n"+"[A_QR_Normal_Scan > QR 스캔 정보 확인 실시]");
//                        Log.d("","\n"+"[결과 : "+String.valueOf(result_data)+"]");
//                        Log.w("//===========//","================================================");
//                        Log.d("---","---");
//
//                        //TODO [팝업창 호출 실시]
//                        String alertTittle = "[QR 스캔 정보 확인]";
//                        String alertMessage = "[정보]"+"\n"+"\n"+String.valueOf(result_data);
//                        String buttonYes = "다시 스캔";
//                        String buttonNo = "종료";
//                        new AlertDialog.Builder(A_QR_Scan.this)
//                                .setTitle(alertTittle)
//                                .setMessage(alertMessage)
//                                .setCancelable(false)
//                                .setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        //TODO Auto-generated method stub
//                                        //TODO [다시 스캔을 하기 위해 플래그값 변경]
//                                        captureFlag = false;
//                                    }
//                                })
//                                .setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // TODO Auto-generated method stub
//                                        try {
//                                            //TODO [액티비티 종료 실시]
//                                            finish();
//                                            overridePendingTransition(0,0);
//                                        }
//                                        catch (Exception e){
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                })
//                                .show();
//
//                        //TODO [플래그 값을 변경 실시 : 중복 스캔 방지]
//                        captureFlag = true;
//                    }
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void possibleResultPoints(List<ResultPoint> resultPoints) {
//            }
//        });
//
    }

}

