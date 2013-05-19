//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.ventura.musicexplorer.ui.artist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.ventura.musicexplorer.R.layout;
import com.ventura.musicexplorer.entity.Image;
import com.ventura.musicexplorer.entity.artist.Artist;

public final class ArtistViewerActivity_
    extends ArtistViewerActivity
{

    private Handler handler_ = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.artist_info);
    }

    private void init_(Bundle savedInstanceState) {
    }

    private void afterSetContentView_() {
        mActivityLoadingBar = ((ProgressBar) findViewById(com.ventura.musicexplorer.R.id.loadingArtistInfoProgressBar));
        mArtistImageDownloadProgressBar = ((ProgressBar) findViewById(android.R.id.progress));
        artistImageGallery = ((Gallery) findViewById(com.ventura.musicexplorer.R.id.artist_images_gallery));
        mArtistImageView = ((ImageView) findViewById(com.ventura.musicexplorer.R.id.artist_image));
        mArtistBio = ((TextView) findViewById(com.ventura.musicexplorer.R.id.artist_bio));
        mBaseLayout = ((LinearLayout) findViewById(com.ventura.musicexplorer.R.id.artist_info));
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

    public static ArtistViewerActivity_.IntentBuilder_ intent(Context context) {
        return new ArtistViewerActivity_.IntentBuilder_(context);
    }

    @Override
    public void afterDownloadArtistMainImage(final Image image) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    ArtistViewerActivity_.super.afterDownloadArtistMainImage(image);
                } catch (RuntimeException e) {
                    Log.e("ArtistViewerActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void fillView(final Artist artist) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    ArtistViewerActivity_.super.fillView(artist);
                } catch (RuntimeException e) {
                    Log.e("ArtistViewerActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void onArtistMainImageDownloadError() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    ArtistViewerActivity_.super.onArtistMainImageDownloadError();
                } catch (RuntimeException e) {
                    Log.e("ArtistViewerActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void getArtist(final int artistId) {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    ArtistViewerActivity_.super.getArtist(artistId);
                } catch (RuntimeException e) {
                    Log.e("ArtistViewerActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void downloadMainImage(final Image image) {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    ArtistViewerActivity_.super.downloadMainImage(image);
                } catch (RuntimeException e) {
                    Log.e("ArtistViewerActivity_", "A runtime exception was thrown while executing code in a runnable", e);
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
            intent_ = new Intent(context, ArtistViewerActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public ArtistViewerActivity_.IntentBuilder_ flags(int flags) {
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
