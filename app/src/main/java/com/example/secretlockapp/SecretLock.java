package com.example.secretlockapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.widget.ListView;

import com.example.secretlockapp.adapters.ItemAdapter;
import com.example.secretlockapp.models.Item;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class SecretLock {

    private HashMap<String, Boolean> getPreferenceValues(Context context) {
        SharedPreferences pref = context.getSharedPreferences("system_settings", MODE_PRIVATE);
        boolean p_wifi = pref.getBoolean("wifi_status", false);
        boolean p_bluetooth = pref.getBoolean("bluetooth_status", false);
        boolean p_airplane = pref.getBoolean("airplanemode_status", false);

        HashMap<String, Boolean> _map = new HashMap<String, Boolean>();
        _map.put("wifi_status", p_wifi);
        _map.put("bluetooth_status", p_bluetooth);
        _map.put("airplanemode_status", p_airplane);

        return _map;

    }

    private HashMap<String, Boolean> getSystemValues(Context context) {
        int wifi_status = Integer.parseInt(Settings.System.getString(context.getContentResolver(), Settings.System.WIFI_ON));
        int bluetooth_status = Integer.parseInt(Settings.System.getString(context.getContentResolver(), Settings.System.BLUETOOTH_ON));
        int airplanemode_status = Integer.parseInt(Settings.System.getString(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON));

        HashMap<String, Boolean> _map = new HashMap<String, Boolean>();
        _map.put("wifi_status", getBoolean(wifi_status));
        _map.put("bluetooth_status", getBoolean(bluetooth_status));
        _map.put("airplanemode_status", getBoolean(airplanemode_status));

        return _map;

    }

    void setPreferenceValues(Context context, HashMap<String, Boolean> _map) {
        SharedPreferences pref = context.getSharedPreferences("system_settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (_map.containsKey("wifi_status")) {
            editor.putBoolean("wifi_status", _map.get("wifi_status"));
        }
        if (_map.containsKey("bluetooth_status")) {
            editor.putBoolean("bluetooth_status", _map.get("bluetooth_status"));
        }
        if (_map.containsKey("airplanemode_status")) {
            editor.putBoolean("airplanemode_status", _map.get("airplanemode_status"));
        }


        editor.apply();


    }

    private boolean getBoolean(int a) {
        return a != 0;
    }

    boolean getLockValue(Context context) {

        HashMap<String, Boolean> preference = getPreferenceValues(context);
        HashMap<String, Boolean> system = getSystemValues(context);

//        Toast.makeText(context, "wifi value : " + preference.get("wifi_status") +
//                "\nbluetooth value : " + preference.get("bluetooth_status") + "\nairplane value : "
//                + preference.get("airplanemode_status"), Toast.LENGTH_SHORT).show();
//
//        Toast.makeText(context, "wifi value : " + system.get("wifi_status") +
//                "\nbluetooth value : " + system.get("bluetooth_status") + "\nairplane value : "
//                + system.get("airplanemode_status"), Toast.LENGTH_SHORT).show();

        return preference.equals(system);

    }

    void openSettings(Context context) {

        ListView listView = new ListView(context);
        ArrayList<Item> itemList = new ArrayList<Item>();
        itemList.add(new Item("wifi", true));
        itemList.add(new Item("bluetooth", false));
        itemList.add(new Item("airplane mode", false));
        ItemAdapter adapter = new ItemAdapter(context, itemList);
        listView.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setView(listView);
        builder.setTitle("Change Settings");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
