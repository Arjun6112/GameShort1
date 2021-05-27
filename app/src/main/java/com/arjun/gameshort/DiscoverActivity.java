package com.arjun.gameshort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DiscoverActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);



        bottomNavigationView=findViewById(R.id.bottom_nav);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.account:
                        Intent a = new Intent(DiscoverActivity.this,loginActivity.class);
                        startActivity(a);
                        break;
                    case R.id.discover:
                        Toast.makeText(DiscoverActivity.this,
                                "You are already in the selected Activity", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.home:
                        Intent c = new Intent(DiscoverActivity.this,MainActivity.class);
                        startActivity(c);
                        break;
                    case R.id.upload:
                        Intent b = new Intent(DiscoverActivity.this,uploadActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });





    }
}