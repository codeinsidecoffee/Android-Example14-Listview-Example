package com.mrlonewolfer.example45;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewCustomAdapter extends BaseAdapter {
    ArrayList<PersonBean> personBeanList;
    public ListViewCustomAdapter(ArrayList<PersonBean> personBeanList) {
        this.personBeanList=personBeanList;
    }

    @Override
    public int getCount() {
        return personBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtName;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            convertView=inflater.inflate(R.layout.list_view_row_item,parent,false);
        }

        PersonBean personBean=personBeanList.get(position);
        txtName=convertView.findViewById(R.id.txtName);
        txtName.setText(personBean.getName());

        return convertView;
    }
}
