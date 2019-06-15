package com.mrlonewolfer.example45;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/*
 Write a program to add name from the text view and insert into the listview.

    If you click on the list view then name should be show in the dialog box.

    When user press for 2 seconds on particular List item then
        open Context Menu (Delete Item, Edit Item, Exit).

        If user click on Delete Item then
            Open one Alert Dialog with message
            (“Are you sure want to delete Item?”) and yes, no button

            if user press yes button then remove item from list.
            (Click a DELETE button, it gives a confirm box)

            If user click on Edit item then
                selected item display on EditText and
                again user click on button then this (updated item) should be replace with old item.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     Fragment fragment= new MainFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameContainer,fragment);
        fragmentTransaction.commit();




    }



}
