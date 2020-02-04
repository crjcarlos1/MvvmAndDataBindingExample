package com.cralos.myapplicationmvvm.films.repository;

import android.util.Log;

import com.cralos.myapplicationmvvm.films.interfaces.FilmsServices;
import com.cralos.myapplicationmvvm.films.interfaces.FilmsViewModel;
import com.cralos.myapplicationmvvm.films.models.Film;
import com.cralos.myapplicationmvvm.films.models.FilmsResponse;
import com.cralos.myapplicationmvvm.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilmsRepository implements com.cralos.myapplicationmvvm.films.interfaces.FilmsRepository {

    private FilmsViewModel viewModel;
    private Retrofit retrofit;
    private FilmsServices services;

    public FilmsRepository(FilmsViewModel viewModel) {
        this.viewModel = viewModel;
        this.retrofit = RetrofitClient.getRetrofitInstance();
        this.services = retrofit.create(FilmsServices.class);
    }

    @Override
    public void getFilms() {
        Call<FilmsResponse> call = services.getFilms();
        call.enqueue(new Callback<FilmsResponse>() {
            @Override
            public void onResponse(Call<FilmsResponse> call, Response<FilmsResponse> response) {
                checkResponse(response);
            }

            @Override
            public void onFailure(Call<FilmsResponse> call, Throwable t) {
                Log.e("TAG", "ERROR: " + t.getMessage());
            }
        });
    }

    private void checkResponse(Response<FilmsResponse> response) {
        if (response.isSuccessful()) {
            checkResponseCode(response);
        } else {
            Log.e("TAG", "ERROR is not successful: ");
        }
    }

    private void checkResponseCode(Response<FilmsResponse> response) {
        if (response.code() == 200) {
            getFilmsResponse(response);
        } else {
            Log.e("TAG", "ERROR response code: ");
        }
    }

    private void getFilmsResponse(Response<FilmsResponse> filmResponse) {
        FilmsResponse response = filmResponse.body();
        if (response != null) {
            List<Film> films = response.getFilms();
            if (filmResponse != null && films.size() > 0) {
                viewModel.setFilms(films);
            } else {
                Log.e("TAG", "ERROR no se enconytraron peliculas: ");
            }
        } else {
            Log.e("TAG", "ERROR filmResponse: ");
        }

    }

}
