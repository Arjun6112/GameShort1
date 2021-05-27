package com.arjun.gameshort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class loginActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        bottomNavigationView=findViewById(R.id.bottom_nav);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.account:
                        Toast.makeText(loginActivity.this,
                                "You are already in the selected Activity", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.discover:
                        Intent b = new Intent(loginActivity.this,DiscoverActivity.class);
                        startActivity(b);
                        break;
                    case R.id.home:
                        Intent a = new Intent(loginActivity.this,loginActivity.class);
                        startActivity(a);
                        break;
                    case R.id.upload:
                        Intent c = new Intent(loginActivity.this,uploadActivity.class);
                        startActivity(c);
                        break;
                }
                return false;
            }
        });



    }
}