package com.example.videoplay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.videoplay.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class VideoPlayerActivity extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        /* Implementação antiga
        VideoView videoView = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        */

        String urlVideo = "https://firebasestorage.googleapis.com/v0/b/videoplay-3c1b2.appspot.com/o/videos123a2695-956b-403d-bcbf-28960d44cb26?alt=media&token=168e0ad4-4771-48fb-9455-c980873b3e2e";

        playerView = findViewById(R.id.video_view);

        // Criar o player
        player = new SimpleExoPlayer.Builder(this).build();

        // Vincular o player à view
        playerView.setPlayer(player);

        // Criar uma fonte de mídia
        MediaSource mediaSource = buildMediaSource(Uri.parse(urlVideo));

        // Preparar o player com a fonte de mídia
        player.prepare(mediaSource);
        player.setPlayWhenReady(true); // Iniciar a reprodução automaticamente
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(uri));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

}