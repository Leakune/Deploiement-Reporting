package com.ludovic.psvsxbox;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class XBOX extends AppCompatActivity {
    Button btn_buy;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xbox);

        btn_buy = findViewById(R.id.btn_buy);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(XBOX.this)
                        .setTitle(getResources().getString(R.string.pop_buy))
                        .setMessage(getResources().getString(R.string.pop_buy_XBOX))
                        .setPositiveButton(getResources().getString(R.string.pop_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent in = new Intent(XBOX.this, MainActivity.class);
                                startActivity(in);
                                finish();
                            }
                        })
                        .show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(XBOX.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}