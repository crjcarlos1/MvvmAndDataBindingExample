package com.cralos.myapplicationmvvm.people.repository;

import android.net.Uri;

import com.cralos.myapplicationmvvm.people.interfaces.PeopleRepository;
import com.cralos.myapplicationmvvm.people.interfaces.PeopleViewModel;
import com.cralos.myapplicationmvvm.people.interfaces.ServicesPeople;
import com.cralos.myapplicationmvvm.people.models.Character;
import com.cralos.myapplicationmvvm.people.models.PeopleResponse;
import com.cralos.myapplicationmvvm.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PeopleRepositoryImpl implements PeopleRepository {

    /*viewModel*/
    private PeopleViewModel viewModel;

    private Retrofit retrofit;
    private ServicesPeople servicesPeople;

    private String nextPage = "";
    private int currentPage = 1;

    public PeopleRepositoryImpl(PeopleViewModel viewModel) {
        this.viewModel = viewModel;
        retrofit = RetrofitClient.getRetrofitInstance();
        servicesPeople = retrofit.create(ServicesPeople.class);
    }

    @Override
    public void getPeople() {
        Call<PeopleResponse> call = servicesPeople.getPeople(currentPage);
        call.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                validateResponseSuccessfull(response);
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {
                viewModel.setMessage(t.getMessage());
            }
        });
    }

    private void validateResponseSuccessfull(Response<PeopleResponse> response) {
        if (response.isSuccessful()) {
            validateResponseCode(response);
        } else {
            viewModel.setMessage("Ocurrio un error");
        }
    }

    private void validateResponseCode(Response<PeopleResponse> response) {
        if (response.code() == 200) {
            getPeopleResponse(response);
        } else {
            viewModel.setMessage("Ocurrio un error");
        }
    }

    private void getPeopleResponse(Response<PeopleResponse> peopleResponse) {
        PeopleResponse response = peopleResponse.body();
        if (response != null) {
            List<Character> characters = response.getCharacters();
            if (characters != null && characters.size() > 0) {
                nextPage = response.getNext();
                if (nextPage != null) {
                    Uri uri = Uri.parse(nextPage);
                    String pageAPI = uri.getQueryParameter("page");
                    currentPage = Integer.parseInt(pageAPI);
                }
                viewModel.setCharacters(characters, nextPage);
            } else {
                viewModel.setMessage("sin personajes");
            }
        } else {
            viewModel.setMessage("Ocurrio un error");
        }
    }

}
