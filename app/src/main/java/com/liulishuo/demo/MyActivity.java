package com.liulishuo.demo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

public class MyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ListView listView = (ListView) findViewById(R.id.listView);
        String[] strings = {"a","b","c"};
        MyAdapter mBaseAdapter = new MyAdapter(getApplicationContext(),strings);
        listView.setAdapter(mBaseAdapter);
    }
}