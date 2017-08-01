package com.quickblox.ui.kit.chatmessage.adapter.media.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.quickblox.ui.kit.chatmessage.adapter.R;
import com.quickblox.ui.kit.chatmessage.adapter.media.MediaController;

/**
 * Created by roman on 8/1/17.
 */

public class QBPlaybackControlView extends PlaybackControlView {
    private static String TAG = QBPlaybackControlView.class.getSimpleName();

    private final View playButton;
    private final View pauseButton;

    private final ComponentListener componentListener;
    private MediaController mediaController;


    public QBPlaybackControlView(Context context) {
        this(context, null);
    }

    public QBPlaybackControlView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QBPlaybackControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        componentListener = new ComponentListener();
        playButton = findViewById(R.id.exo_play);
        if (playButton != null) {
            playButton.setOnClickListener(componentListener);
        }
        pauseButton = findViewById(R.id.exo_pause);
        if (pauseButton != null) {
            pauseButton.setOnClickListener(componentListener);
        }
        alwaysShow();
    }

    private void alwaysShow() {
        setShowTimeoutMs(0);
        show();
    }

    public void initMediaController(MediaController mediaController) {
        this.mediaController = mediaController;
    }

    public void restoreState(ExoPlayer player) {
        setPlayer(player);
    }

    public void releaseView() {
        Log.d(TAG, "releaseView view");
        if (this.getPlayer() != null) {
           setPlayer(null);
        }
    }

    public boolean isCurrentViewPlaying() {
        return getPlayer() != null;
    }

    private final class ComponentListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (playButton == view) {
                mediaController.onPlayClicked(QBPlaybackControlView.this);
            } else if (pauseButton == view) {
                mediaController.onPauseClicked(view);
            }
        }
    }

}