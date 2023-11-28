package com.example.videoplay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.videoplay.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class VideoPlayerCustom extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView playerView;

    private ImageView scaling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sumir com a StatusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_video_player_custom);

        String urlVideo = "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4";
        //String urlVideo = "https://firebasestorage.googleapis.com/v0/b/videoplay-3c1b2.appspot.com/o/videos123a2695-956b-403d-bcbf-28960d44cb26?alt=media&token=168e0ad4-4771-48fb-9455-c980873b3e2e";

        playerView = findViewById(R.id.exoplayer_view);

        // Criar o player
        player = new SimpleExoPlayer.Builder(this).build();

        // Vincular o player à view
        playerView.setPlayer(player);

        // Criar uma fonte de mídia
        MediaSource mediaSource = buildMediaSource(Uri.parse(urlVideo));

        // Preparar o player com a fonte de mídia
        player.prepare(mediaSource);
        player.setPlayWhenReady(true); // Iniciar a reprodução automaticamente

        findViewById(R.id.video_back).setOnClickListener(click -> finish());

    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(uri));
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        releasePlayer();
//    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pausar o reprodutor de vídeo
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Retomar a reprodução do vídeo, se necessário
        if (player != null) {
            player.setPlayWhenReady(true);
        }
    }

}