package com.example.ym.flashlight;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by YM on 2017/3/8.
 */

public class FlashFrag extends Fragment {
    private CameraManager mCameraManager;
    public static ImageButton mTorchButton;
    private String cameraId;
    public static boolean isTorch;
    public static NotificationManager notificationManager;

    public static final String CLOSE_FLASH = "com.android.broadcast.closeflash";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flashfrag,container,false);


        mTorchButton = (ImageButton)view.findViewById(R.id.isTorch);




        mCameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);



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

                }else{
                    trunOffFlashLight();

                }
            }
        });


        return view;


    }


    private void trunOnFlashLight(){
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                mCameraManager.setTorchMode(cameraId,true);
                mTorchButton.setImageResource(R.drawable.on);
                openNotification();
                isTorch = false;

            }
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }



    private void trunOffFlashLight(){
        try{
            mCameraManager.setTorchMode(cameraId,false);
            mTorchButton.setImageResource(R.drawable.off);
            closeNotification();
            isTorch = true;

        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }



    private void openNotification(){

        notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(getContext())
                .setContentText("闪光灯正在运行")
                .setContentTitle("点击关闭闪光灯")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.on))
                .setSmallIcon(R.drawable.on)
                .setContentIntent(pendingIntent())
                .build();
        notificationManager.notify(1,notification);

    }

    private void closeNotification(){

        notificationManager = (NotificationManager)getActivity().getSystemService(NOTIFICATION_SERVICE);

        notificationManager.cancel(1);

    }

    private PendingIntent pendingIntent(){
        Intent intent = new Intent();
        intent.setAction(CLOSE_FLASH);
        intent.setClass(getContext(),FlashClose.class);


        return PendingIntent.getBroadcast(getContext(),0,intent,0);
    }






}

