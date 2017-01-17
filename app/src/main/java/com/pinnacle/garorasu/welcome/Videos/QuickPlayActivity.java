package com.pinnacle.garorasu.welcome.Videos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.pinnacle.garorasu.welcome.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class QuickPlayActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);
        Intent intent = getIntent();
        final Video video = (Video) intent.getSerializableExtra("Video");

        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize("AIzaSyBou_MS25E8rYwumqS-y125gHJdByt2dvY",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.loadVideo(video.getVid());
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(getApplicationContext(),"Update your youtube app to play this video",Toast.LENGTH_SHORT);
                     System.out.println("Error for youtube initialization : "+youTubeInitializationResult);
                    }
                });
    }

}
