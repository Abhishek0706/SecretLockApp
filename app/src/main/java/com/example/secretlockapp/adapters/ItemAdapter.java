package com.example.secretlockapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.secretlockapp.R;
import com.example.secretlockapp.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private List<Item> itemList = new ArrayList<Item>();


    public ItemAdapter(@NonNull Context context, ArrayList<Item> itemList) {
        super(context, 0, itemList);
        mContext = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        }

        Item item = getItem(position);

        TextView name = convertView.findViewById(R.id.textview_name);
        Switch value = convertView.findViewById(R.id.switch_value);

        if (item != null) {
            name.setText(item.getName());
            value.setChecked(item.getValue());
        }

        return convertView;

    }
}
