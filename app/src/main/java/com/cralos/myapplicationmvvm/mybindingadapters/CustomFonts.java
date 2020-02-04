package com.cralos.myapplicationmvvm.mybindingadapters;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class CustomFonts {

    @BindingAdapter("android:customFont")
    public static void customFontTextView(View view, String fontName) {
        TextView textView = (TextView) view;
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
    }

}
