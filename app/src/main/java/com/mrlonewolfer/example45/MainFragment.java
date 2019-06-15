package com.mrlonewolfer.example45;

;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    EditText edtName;
    Button btnSubmit;
    PersonBean personBean;
    ListView listView;
    ArrayList<PersonBean> personBeanList;
    Context context;
    ListViewCustomAdapter adapter;
    AdapterView.AdapterContextMenuInfo info;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context=getContext();
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        edtName=view.findViewById(R.id.edtName);
        btnSubmit=view.findViewById(R.id.btnSubmit);
        listView=view.findViewById(R.id.listView);
       registerForContextMenu(listView);
        btnSubmit.setOnClickListener(this);



        personBeanList= new ArrayList<>();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personBean=personBeanList.get(position);
                Toast.makeText(context, " Name is: "+personBean.Name, Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select The Action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
  String name=personBeanList.get(info.position).Name;
        switch(item.getItemId()){
                        case R.id.Delete:

                            AlertDialog.Builder builder= new AlertDialog.Builder(context);
                            builder.setTitle("You Trying to Remove Name");
                            builder.setMessage("Are You Sure You Want to Remove Item?");
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    personBeanList.remove(info.position);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "No iteam Will remove", Toast.LENGTH_SHORT).show();

                                }
                            });
                            builder.show();

                            break;
                        case R.id.Edit:
                            edtName.setText(name);
                            btnSubmit.setText("Update");
                           // Toast.makeText(context, "Edit Content "+name, Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.Exit:
                            Toast.makeText(context, "Exit", Toast.LENGTH_SHORT).show();
                            break;

                    }
        adapter.notifyDataSetChanged();
        return true;
    }

    private void preparedSubmitData() {

        personBeanList.add(new PersonBean(edtName.getText().toString()));


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSubmit){
            String mySubmitButton=btnSubmit.getText().toString();
            if(mySubmitButton.equals("Save")) {

                if (edtName.getText().toString() != "") {
                    preparedSubmitData();
                    adapter = new ListViewCustomAdapter(personBeanList);
                    listView.setAdapter(adapter);
                    edtName.setText("");

                } else {
                    Toast.makeText(context, "Please Enter Name First", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                if (edtName.getText().toString() != "") {
                    personBeanList.set(info.position,new PersonBean(edtName.getText().toString()));

                    adapter = new ListViewCustomAdapter(personBeanList);
                    listView.setAdapter(adapter);
                    edtName.setText("");

                } else {
                    Toast.makeText(context, "Please Enter Name First", Toast.LENGTH_SHORT).show();
                }

            }
        }
//        if(v.getId()==R.id.listView){
//            PopupMenu popupMenu= new PopupMenu(context,v);
//            popupMenu.getMenuInflater().inflate(R.menu.context_menu,popupMenu.getMenu());
//            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    switch(item.getItemId()){
//                        case R.id.Delete:
//                            Toast.makeText(context, "Delete Content", Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.Edit:
//                            Toast.makeText(context, "Edit Content", Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.Exit:
//                            Toast.makeText(context, "Exit", Toast.LENGTH_SHORT).show();
//                            break;
//
//                    }
//                    return false;
//                }
//            });
//        }
    }
}
