package com.popland.pop.myvideonews_youtubeapi;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Main3Activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
String APIkey = "AIzaSyDZ4vS-D1W0pgEcWy6WMZcy0IUK63ELBKI";
    YouTubePlayerView youTubePlayerView;
    int REQUEST_CODE =999;
    String videoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent i = getIntent();
        videoId = i.getStringExtra("videoId");
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youTubePlayerView);
        youTubePlayerView.initialize(APIkey,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(videoId);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
         if(youTubeInitializationResult.isUserRecoverableError()){
             youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE);
         }else{
             Toast.makeText(this,"loi video",Toast.LENGTH_LONG).show();
         }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            youTubePlayerView.initialize(APIkey,this);
        }
    }
}
