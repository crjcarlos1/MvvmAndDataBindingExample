package com.cralos.myapplicationmvvm.films.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.myapplicationmvvm.films.interfaces.FilmsRepository;
import com.cralos.myapplicationmvvm.films.interfaces.FilmsViewModel;
import com.cralos.myapplicationmvvm.films.models.Film;

import java.util.List;

public class FilmsModel extends ViewModel implements FilmsViewModel {

    /*repository*/
    private FilmsRepository repository;

    /*write*/
    private MutableLiveData<List<Film>> films;

    /*read*/
    public LiveData<List<Film>> getFilms() {
        return films;
    }

    private MutableLiveData<Boolean> isLoading;

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void init() {
        if (films == null) {
            films = new MutableLiveData<>();
            isLoading = new MutableLiveData<>();
            repository = new com.cralos.myapplicationmvvm.films.repository.FilmsRepository(this);
        }
    }

    public void getFilmsFromApi() {
        isLoading.setValue(true);
        repository.getFilms();
    }

    @Override
    public void setFilms(List<Film> films) {
        isLoading.setValue(false);
        this.films.setValue(films);
    }

}
