package com.cralos.myapplicationmvvm.films.interfaces;

import com.cralos.myapplicationmvvm.films.models.FilmsResponse;
import com.cralos.myapplicationmvvm.retrofit.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmsServices {
    /**
     * @return
     */
    @GET(EndPoints.FILMS)
    Call<FilmsResponse> getFilms();
}
