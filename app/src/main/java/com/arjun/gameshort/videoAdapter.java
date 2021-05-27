package com.arjun.gameshort;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class videoAdapter extends RecyclerView.Adapter<videoAdapter.myViewHolder> {

    ArrayList<videoModel> videos ;

    public videoAdapter(ArrayList<videoModel> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_video,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.setData(videos.get(position));

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    class myViewHolder extends RecyclerView.ViewHolder {

        VideoView videoView;
        TextView tvTitle,tvDescription;
        LottieAnimationView loadingAnim;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            videoView=itemView.findViewById(R.id.videoView2);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            loadingAnim=itemView.findViewById(R.id.loadingbar);
        }
        void setData(videoModel obj){
            videoView.setVideoPath(obj.getVideoUrl());
            tvTitle.setText(obj.getvTitle());
            tvDescription.setText(obj.getvDes());

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    loadingAnim.setVisibility(View.GONE);
                    mp.start();

                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                    
                }
            });


        }

    }
}
