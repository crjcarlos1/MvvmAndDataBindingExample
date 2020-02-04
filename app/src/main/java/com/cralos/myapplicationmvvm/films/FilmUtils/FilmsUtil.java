package com.cralos.myapplicationmvvm.films.FilmUtils;

import android.content.Context;
import android.util.Log;

import com.cralos.myapplicationmvvm.R;

public class FilmsUtil {

    public static String getNameAndEpisode(String title, int episode, Context context) {
        String string = context.getString(R.string.app_name);
        Log.e("PRUEBA", "::: " + string);
        return title + ", episode " + episode;
    }

}
