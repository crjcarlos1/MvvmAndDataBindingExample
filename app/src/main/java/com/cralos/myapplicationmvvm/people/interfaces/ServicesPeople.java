package com.cralos.myapplicationmvvm.people.interfaces;

import com.cralos.myapplicationmvvm.people.models.PeopleResponse;
import com.cralos.myapplicationmvvm.retrofit.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicesPeople {

    /**
     * @param page
     * @return
     */
    @GET(EndPoints.PEOPLE)
    Call<PeopleResponse> getPeople(@Query("page") int page);

}
