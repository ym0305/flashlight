package com.example.ym.flashlight;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private CameraManager mCameraManager;
    private ImageButton mTorchButton;
    private String cameraId;
    private boolean isTorch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTorchButton = (ImageButton)findViewById(R.id.isTorch);
        mCameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        isTorch = true;
        try{
            cameraId = mCameraManager.getCameraIdList()[0];
        }catch (CameraAccessException e){
            e.printStackTrace();
        }

        mTorchButton.setOnClickListener(new View.OnClickListener() {
            @Override



            public void onClick(View view) {



                if (isTorch){
                    trunOnFlashLight();
                    isTorch = false;

                }else{
                    trunOffFlashLight();
                    isTorch = true;


                }
            }
        });


    }

    private void trunOnFlashLight(){
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                mCameraManager.setTorchMode(cameraId,true);
                mTorchButton.setImageResource(R.drawable.on);
            }
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    private void trunOffFlashLight(){
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                mCameraManager.setTorchMode(cameraId,false);
                mTorchButton.setImageResource(R.drawable.off);
            }
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }


}
