package com.arjun.gameshort;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;

public class uploadActivity extends AppCompatActivity {

    private static final int PICK_VIDEO=1;
    VideoView videoView;
    Button button;
    EditText editText;
    ProgressBar progressBar;
    private Uri videoUri;
    MediaController mediaController;
    BottomNavigationView bottomNavigationView;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    Member member;
    UploadTask uploadTask;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        member=new Member();
        storageReference= FirebaseStorage.getInstance().getReference("Video");
        databaseReference= FirebaseDatabase.getInstance().getReference("video");


        videoView = findViewById(R.id.videoView);
        button = findViewById(R.id.uploadButton);
        editText = findViewById(R.id.videoTitle);
        progressBar = findViewById(R.id.ProgressBar);

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();


        bottomNavigationView=findViewById(R.id.bottom_nav);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.account:
                        Intent a = new Intent(uploadActivity.this,loginActivity.class);
                        startActivity(a);
                        break;
                    case R.id.discover:
                        Intent b = new Intent(uploadActivity.this,DiscoverActivity.class);
                        startActivity(b);
                        break;
                    case R.id.home:
                        Intent c = new Intent(uploadActivity.this,MainActivity.class);
                        startActivity(c);
                        break;
                    case R.id.upload:
                        Toast.makeText(uploadActivity.this,
                                "You are already in the selected Activity", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadVideo();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==PICK_VIDEO || resultCode ==RESULT_OK || data!=null || data.getData()!=null){

            videoUri = data.getData();
            videoView.setVideoURI(videoUri);



        }
    }

    public void chooseVideo(View view) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_VIDEO);

    }

    private String getExt(Uri uri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap= MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));


    }



    private void UploadVideo(){
        String videoName= editText.getText().toString();
        String search= editText.getText().toString().toLowerCase();

        if(videoUri != null || !TextUtils.isEmpty(videoName)){
            progressBar.setVisibility(View.VISIBLE);
            final StorageReference refrence = storageReference.child(System.currentTimeMillis() + "." + getExt(videoUri));
            uploadTask= refrence.putFile(videoUri);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return refrence.getDownloadUrl();
                }
            }) .addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUrl = task.getResult();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(uploadActivity.this,"Video Uploaded",Toast.LENGTH_SHORT).show();


                        member.setName(videoName);
                        member.setVideoURl(downloadUrl.toString());
                        member.setSearch(search);
                        String i =databaseReference.push().getKey();
                        databaseReference.child(i).setValue(member);
                    }
                    else{
                        Toast.makeText(uploadActivity.this,"Video Upload Failed",Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }
        else{
            Toast.makeText(uploadActivity.this,"All Fields are required",Toast.LENGTH_SHORT).show();
        }
    }
}