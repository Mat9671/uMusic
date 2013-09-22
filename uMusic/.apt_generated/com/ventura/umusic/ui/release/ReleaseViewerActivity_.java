//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.ventura.umusic.ui.release;

import android.R.anim;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.ventura.umusic.R.id;
import com.ventura.umusic.R.layout;
import com.ventura.umusic.discogs.entity.Artist;
import com.ventura.umusic.discogs.entity.ArtistRelease;
import com.ventura.umusic.discogs.entity.Release;
import com.ventura.umusic.ui.widget.ButtonGroup;
import com.ventura.umusic.ui.widget.KeyValuePanel;

public final class ReleaseViewerActivity_
    extends ReleaseViewerActivity
{

    private Handler handler_ = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.release_info);
    }

    private void init_(Bundle savedInstanceState) {
        fadeIn = AnimationUtils.loadAnimation(this, anim.fade_in);
    }

    private void afterSetContentView_() {
        tracksContainer = ((LinearLayout) findViewById(id.tracks_list_container));
        country = ((KeyValuePanel) findViewById(id.country));
        artists = ((ButtonGroup) findViewById(id.artists_button_group));
        extraArtists = ((ButtonGroup) findViewById(id.extra_artists_button_group));
        notes = ((TextView) findViewById(id.notes));
        thumb = ((ImageView) findViewById(id.release_image));
        year = ((KeyValuePanel) findViewById(id.year));
        loadingProgressBar = ((ProgressBar) findViewById(id.loadingReleaseInfoProgressBar));
        contentContainer = ((LinearLayout) findViewById(id.content));
        afterViews();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    public static ReleaseViewerActivity_.IntentBuilder_ intent(Context context) {
        return new ReleaseViewerActivity_.IntentBuilder_(context);
    }

    @Override
    public void updateView(final Release release) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    ReleaseViewerActivity_.super.updateView(release);
                } catch (RuntimeException e) {
                    Log.e("ReleaseViewerActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void openArtistInfo(final Artist artist) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    ReleaseViewerActivity_.super.openArtistInfo(artist);
                } catch (RuntimeException e) {
                    Log.e("ReleaseViewerActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void getRelease(final ArtistRelease artistRelease) {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    ReleaseViewerActivity_.super.getRelease(artistRelease);
                } catch (RuntimeException e) {
                    Log.e("ReleaseViewerActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, ReleaseViewerActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public ReleaseViewerActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (context_ instanceof Activity) {
                ((Activity) context_).startActivityForResult(intent_, requestCode);
            } else {
                context_.startActivity(intent_);
            }
        }

    }

}
