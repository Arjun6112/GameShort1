package com.arjun.gameshort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ArrayList<videoModel> videos;

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        bottomNavigationView=findViewById(R.id.bottom_nav);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.account:
                        Intent a = new Intent(MainActivity.this,loginActivity.class);
                        startActivity(a);
                        break;
                    case R.id.discover:
                        Intent b = new Intent(MainActivity.this,DiscoverActivity.class);
                        startActivity(b);
                        break;
                    case R.id.home:
                        Toast.makeText(MainActivity.this,
                                "You are already in the selected Activity", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.upload:
                        Intent c = new Intent(MainActivity.this,uploadActivity.class);
                        startActivity(c);
                    break;
                }
                return false;
            }
        });

        viewPager2 = (ViewPager2) findViewById(R.id.viewpager2);
        videos=new ArrayList<>();



        videoModel ob1= new videoModel("https://firebasestorage.googleapis.com/v0/b/gameshort-d2d56.appspot.com/o/Video%2F1620935498396.mp4?alt=media&token=a3c46df0-2fbf-4ba7-bd5e-d0d30e2b07b8","Cycle Drift","Almost Fell , Crazy times");
        videos.add(ob1);

        videoModel ob2= new videoModel("https://firebasestorage.googleapis.com/v0/b/gameshort-d2d56.appspot.com/o/Video%2F1621708390381.mp4?alt=media&token=eb52b4de-2a4c-4726-aebf-1fb41e1dbb9b","Rainbow"," Pulled a Rainbow Move");
        videos.add(ob2);

        videoModel ob3= new videoModel("https://firebasestorage.googleapis.com/v0/b/gameshort-d2d56.appspot.com/o/Video%2F1621708920772.mp4?alt=media&token=e7ce258a-a6a3-40db-95d8-761694cbcae7","Flying Shoe","Free-kick gone wrong");
        videos.add(ob3);




        viewPager2.setAdapter(new videoAdapter(videos));
    }
}