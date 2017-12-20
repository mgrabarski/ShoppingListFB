package com.mateusz.grabarski.myshoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mateusz.grabarski.myshoppinglist.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
