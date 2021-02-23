package com.ludovic.psvsxbox;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class PS extends AppCompatActivity {
    Button btn_buy;
    private FirebaseAnalytics mFirebaseAnalytics;
    private Bundle psCart;
    private Bundle itemConsole;
    Bundle itemAccessories;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        this.itemConsole = createItem("1",
                "console PS5",
                "console",
                "white",
                "Sony",
                500.0
        );
        this.itemAccessories = createItem("2",
                "accessories PS5",
                "accessories",
                "",
                "Sony",
                50.00
        );


        btn_buy = findViewById(R.id.btn_buy);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PS.this)
                        .setTitle(getResources().getString(R.string.pop_buy))
                        .setMessage(getResources().getString(R.string.pop_buy_PS))
                        .setPositiveButton(getResources().getString(R.string.pop_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent in = new Intent(PS.this, MainActivity.class);
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
                Intent i = new Intent(PS.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    public Bundle createItem(String id, String name, String category, String variant, String brand, Double price){
        Bundle item = new Bundle();
        item.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        item.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        item.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, category);
        item.putString(FirebaseAnalytics.Param.ITEM_VARIANT, variant);
        item.putString(FirebaseAnalytics.Param.ITEM_BRAND, brand);
        item.putDouble(FirebaseAnalytics.Param.PRICE, price);
        return item;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_console_PS:
                if (checked){
                    System.out.println("console checked");
                    /*this.psCart.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
                    this.psCart.putDouble(FirebaseAnalytics.Param.VALUE, 500.0);
                    this.psCart.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                            new Parcelable[]{ this.itemConsole });

                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, this.psCart);*/
                    Bundle itemConsoleWishlist = new Bundle(this.itemConsole);
                    itemConsoleWishlist.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

                    Bundle addToWishlistParams = new Bundle();
                    addToWishlistParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
                    addToWishlistParams.putDouble(FirebaseAnalytics.Param.VALUE, 500.0);
                    addToWishlistParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                            new Parcelable[]{ itemConsoleWishlist });

                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, addToWishlistParams);
                }
                else{
                    System.out.println("console unchecked");
                }
                break;
            case R.id.checkbox_accessoires_PS:
                if (checked){
                    System.out.println("accessories checked");
                }
                else{
                    System.out.println("accessories unchecked");
                }
                break;
        }
    }
}