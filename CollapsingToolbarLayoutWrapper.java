package com.example.bhavesh.taskmanager.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.AttributeSet;

import com.example.bhavesh.taskmanager.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class CollapsingToolbarLayoutWrapper extends CollapsingToolbarLayout {
    public CollapsingToolbarLayoutWrapper(Context context) {
        super(context);
    }

    public CollapsingToolbarLayoutWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CollapsingToolbarLayoutWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        try {
            Context applicationContext = getContext();
            AssetManager assetManager = applicationContext.getAssets();
            String fontBold = applicationContext.getString(R.string.bold);
            this.setCollapsedTitleTypeface(TypefaceUtils.load(assetManager, fontBold));
            this.setExpandedTitleTypeface(TypefaceUtils.load(assetManager, fontBold));
        } catch (Exception e) {
            // Maybe exceptions from typeface, like missing font in assets, for font is not accept,...
        }
    }
}
