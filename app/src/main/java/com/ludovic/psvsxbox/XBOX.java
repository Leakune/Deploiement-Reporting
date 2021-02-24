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

public class XBOX extends AppCompatActivity {
    Button btn_buy;
    private FirebaseAnalytics mFirebaseAnalytics;
    private Bundle itemConsoleCart;
    private Bundle itemConsole;
    private Bundle itemAccessories;
    private Bundle itemAccessoriesCart;
    public Boolean console_checked = false;
    public Boolean accessories_checked = false;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xbox);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setUserId("9999");

        this.itemConsole = createItem("3",
                "console XBOX Series X",
                "console",
                "black",
                "Microsoft",
                500.0
        );
        this.itemAccessories = createItem("4",
                "accessories XBOX",
                "accessories",
                "",
                "Microsoft",
                60.00
        );

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
                                Bundle purchaseParams = new Bundle();
                                purchaseParams.putString(FirebaseAnalytics.Param.TRANSACTION_ID, "T9999");
                                purchaseParams.putString(FirebaseAnalytics.Param.AFFILIATION, "Microsoft");
                                purchaseParams.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
                                purchaseParams.putDouble(FirebaseAnalytics.Param.VALUE, 560.0);
                                purchaseParams.putDouble(FirebaseAnalytics.Param.TAX, 30);
                                purchaseParams.putDouble(FirebaseAnalytics.Param.SHIPPING, 10);
                                purchaseParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                                        new Parcelable[]{ itemConsoleCart, itemAccessoriesCart });

                                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE, purchaseParams);
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
            case R.id.checkbox_console_XBOX:
                if (checked){
                    this.console_checked = true;
                    System.out.println("console checked");
                    this.itemConsoleCart = new Bundle(this.itemConsole);
                    this.itemConsoleCart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

                    Bundle addToCartParams = new Bundle();
                    addToCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
                    addToCartParams.putDouble(FirebaseAnalytics.Param.VALUE, 500.0);
                    addToCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                            new Parcelable[]{ this.itemConsoleCart });

                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, addToCartParams);
                }
                else{
                    this.console_checked = false;
                    System.out.println("console unchecked");
                    Bundle removeCartParams = new Bundle();
                    removeCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
                    removeCartParams.putDouble(FirebaseAnalytics.Param.VALUE, 500.00);
                    removeCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                            new Parcelable[]{ this.itemConsoleCart });

                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.REMOVE_FROM_CART, removeCartParams);
                }
                break;
            case R.id.checkbox_accessoires_XBOX:
                if (checked){
                    this.accessories_checked = true;
                    System.out.println("accessories checked");
                    this.itemAccessoriesCart = new Bundle(this.itemAccessories);
                    this.itemAccessoriesCart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

                    Bundle addToCartParams = new Bundle();
                    addToCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
                    addToCartParams.putDouble(FirebaseAnalytics.Param.VALUE, 50.00);
                    addToCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                            new Parcelable[]{ this.itemAccessoriesCart });

                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, addToCartParams);
                }
                else{
                    this.accessories_checked = false;
                    System.out.println("accessories unchecked");
                    Bundle removeCartParams = new Bundle();
                    removeCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
                    removeCartParams.putDouble(FirebaseAnalytics.Param.VALUE, 50.00);
                    removeCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                            new Parcelable[]{ this.itemAccessoriesCart });

                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.REMOVE_FROM_CART, removeCartParams);
                }
                break;
        }
    }
}