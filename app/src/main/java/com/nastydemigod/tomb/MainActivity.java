package com.nastydemigod.tomb;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {//implements LocationInterface {
   // private LocationManager locationManager;
   // private Listener listener;
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    public EditText editFName, editName, editPatronymic;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isServiceOK()) {
            init();
        }
        creature();
    }

    public void onClickRead (View view) {
        Intent i = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(i);
    }

    public void creature() {
        editFName = findViewById(R.id.editFName);
        editName = findViewById(R.id.editName);
        editPatronymic = findViewById(R.id.editPatronymic);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }
    public boolean isServiceOK() {
        Log.d(TAG, "isServicesOK: checking google service version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available== ConnectionResult.SUCCESS) {
            //все хорошо
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //получили ошибку но знаем какую
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "You can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void init() {
        Button button_Find = (Button) findViewById(R.id.button_Find);
        button_Find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }


}