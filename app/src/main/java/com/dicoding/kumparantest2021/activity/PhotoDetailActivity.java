package com.dicoding.kumparantest2021.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dicoding.kumparantest2021.R;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends AppCompatActivity {

    private static final int ANIM_DURATION = 600;
    private TextView titleTextView;
    private TextView infoTextView;
    private ZoomageView imageView;
    private FrameLayout frameLayout;
    private ColorDrawable mColorDrawable;
    private int mThumbnailTop;
    private int mThumbnailLeft;
    private int mThumbnailWidth;
    private int mThumbnailHeight;
    private int mLeftDelta;
    private int mTopDelta;
    private float mWidthScale;
    private float mHeightScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.hide();

        //retrieves the thumbnail data
        final Bundle bundle = getIntent().getExtras();
        mThumbnailTop = bundle.getInt("top");
        mThumbnailLeft = bundle.getInt("left");
        mThumbnailWidth = bundle.getInt("width");
        mThumbnailHeight = bundle.getInt("height");

        String image = getIntent().getStringExtra("image");

        if(image == null)   finish();

        imageView = (ZoomageView) findViewById(R.id.grid_item_image);

        //Toast.makeText(ImageZoomActivity.this, "image path : " + image, Toast.LENGTH_SHORT).show();
        if (image.contains("http")) {
            Picasso.with(this).load(image).into(imageView);
        }
        else {
            imageView.setImageURI(Uri.parse(image));
        }

        //Set the background color to black
        frameLayout = (FrameLayout) findViewById(R.id.main_background);
        mColorDrawable = new ColorDrawable(Color.BLACK);
        frameLayout.setBackground(mColorDrawable);

        // Only run the animation if we're coming from the parent activity, not if
        // we're recreated automatically by the window manager (e.g., device rotation)
        if (savedInstanceState == null) {
            ViewTreeObserver observer = imageView.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                private void doNothing() {

                }

                @Override
                public boolean onPreDraw() {
                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);

                    int[] screenLocation = new int[2];
                    imageView.getLocationOnScreen(screenLocation);
                    mLeftDelta = mThumbnailLeft - screenLocation[0];
                    mTopDelta = mThumbnailTop - screenLocation[1];

                    // Scale factors to make the large version the same size as the thumbnail
                    mWidthScale = (float) mThumbnailWidth / imageView.getWidth();
                    mHeightScale = (float) mThumbnailHeight / imageView.getHeight();

                    enterAnimation();
                    return true;
                }
            });
        }
    }

    public void enterAnimation() {
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        imageView.setScaleX(mWidthScale);
        imageView.setScaleY(mHeightScale);
        imageView.setTranslationX(mLeftDelta);
        imageView.setTranslationY(mTopDelta);

        // interpolator where the rate of change starts out quickly and then decelerates.
        TimeInterpolator sDecelerator = new DecelerateInterpolator();

        // Animate scale and translation to go from thumbnail to full size
        imageView.animate().setDuration(ANIM_DURATION).scaleX(1).scaleY(1).
                translationX(0).translationY(0).setInterpolator(sDecelerator);

        // Fade in the black background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mColorDrawable, "alpha", 0, 255);
        bgAnim.setDuration(ANIM_DURATION);
        bgAnim.start();
    }

    public void exitAnimation(final Runnable endAction) {
        TimeInterpolator sInterpolator = new AccelerateInterpolator();
        imageView.animate().setDuration(ANIM_DURATION).scaleX(mWidthScale).scaleY(mHeightScale).
                translationX(mLeftDelta).translationY(mTopDelta)
                .setInterpolator(sInterpolator).withEndAction(endAction);

        // Fade out background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mColorDrawable, "alpha", 0);
        bgAnim.setDuration(ANIM_DURATION);
        bgAnim.start();
    }

    @Override
    public void onBackPressed() {
        exitAnimation(new Runnable() {
            public void run() {
                finish();
            }
        });
    }
}
