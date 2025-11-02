package com.example.mvc.screens.common;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mvc.CustomApplication;
import com.example.mvc.common.CompositionRoot;

public class BaseActivity extends AppCompatActivity {

    protected CompositionRoot getCompositionRoot(){
        return ((CustomApplication) getApplication()).getCompositionRoot();
    }

}
