package com.ludovic.psvsxbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private Button btn_PS, btn_XBOX, btn_exit;
    private FirebaseAnalytics mFirebaseAnalytics;
    //private FirebaseCrashlytics​ crash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_PS = findViewById(R.id.btn_PS);
        btn_XBOX = findViewById(R.id.btn_XBOX);
        btn_exit = findViewById(R.id.btn_exit);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        btn_PS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventAnalytics("btn_PS", "bouton ps", "bouton");
                Intent i = new Intent(MainActivity.this, PS.class);
                startActivity(i);
                finish();
            }
        });
        btn_XBOX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventAnalytics("btn_XBOX", "bouton xbox", "bouton");
                Intent i = new Intent(MainActivity.this, XBOX.class);
                startActivity(i);
                finish();
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(getResources().getString(R.string.pop_title))
                        .setPositiveButton(getResources().getString(R.string.pop_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.pop_no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });
        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void eventAnalytics(String id, String name, String type){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, type);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}