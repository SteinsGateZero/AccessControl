package com.vdin.accesscontrol.utils;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

public final class Permission {

    private static final Permission permission = new Permission();

    public static final String[] CALENDAR;
    public static final String[] CAMERA;
    public static final String[] CONTACTS;
    public static final String[] LOCATION;
    public static final String[] MICROPHONE;
    public static final String[] PHONE;
    public static String[] SENSORS;
    public static final String[] SMS;
    public static final String[] STORAGE;

    static {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            CALENDAR = new String[]{};
//            CAMERA = new String[]{};
//            CONTACTS = new String[]{};
//            LOCATION = new String[]{};
//            MICROPHONE = new String[]{};
//            PHONE = new String[]{};
//            SENSORS = new String[]{};
//            SMS = new String[]{};
//            STORAGE = new String[]{};
//        } else {
        CALENDAR = new String[]{
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR};

        CAMERA = new String[]{
                Manifest.permission.CAMERA};

        CONTACTS = new String[]{
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.GET_ACCOUNTS};

        LOCATION = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        MICROPHONE = new String[]{
                Manifest.permission.RECORD_AUDIO};

        PHONE = new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.USE_SIP,
                Manifest.permission.PROCESS_OUTGOING_CALLS};
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            SENSORS = new String[]{
                    Manifest.permission.BODY_SENSORS};
        }

        SMS = new String[]{
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH,
                Manifest.permission.RECEIVE_MMS};

        STORAGE = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        //}
    }

    private Permission() {
    }

    public static Permission getInstance() {
        return permission;
    }

    public void requestPermission(FragmentActivity activity, Consumer<Boolean> consumer, String[]... permissions) {
        List<String> list = new ArrayList<>();
        for (String[] item : permissions) {
            list.addAll(Arrays.asList(item));
        }
        String[] str = new String[list.size()];
        list.toArray(str);
        new RxPermissions(activity).request(str).subscribe(consumer);
    }

    public void requestPermission(FragmentActivity activity, Consumer<Boolean> consumer, String... permissions) {
        new RxPermissions(activity).request(permissions).subscribe(consumer);
    }
}
