package com.cralos.myapplicationmvvm.people.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.myapplicationmvvm.people.interfaces.PeopleRepository;
import com.cralos.myapplicationmvvm.people.models.Character;
import com.cralos.myapplicationmvvm.people.repository.PeopleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class PeopleViewModel extends ViewModel implements com.cralos.myapplicationmvvm.people.interfaces.PeopleViewModel {


    /*repository*/
    private PeopleRepository repository;

    /*writeCharacters*/
    private MutableLiveData<List<Character>> characters;

    /*readCharacters*/
    public LiveData<List<Character>> getCharacters() {
        return characters;
    }

    /*loaderWrite*/
    private MutableLiveData<Boolean> isLoading;

    /*loaderRead*/
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /*current characters*/
    private List<Character> currentCharacters;

    /*para saber si se tiene que realizar otro request*/
    private String nextPage = "";

    public void initPeopleViewModel() {
        this.repository = new PeopleRepositoryImpl(this);
        currentCharacters = new ArrayList<>();
        characters = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        characters.setValue(currentCharacters);
    }

    public void getCharactersFromAPI() {
        if (nextPage != null) { // si hay siguiente pagina obtenemos datos
            isLoading.setValue(true);
            repository.getPeople();
        }
    }

    @Override
    public void setMessage(String message) {
        isLoading.setValue(false);
    }

    @Override
    public void setCharacters(List<Character> newCharacters, String nextPage) {
        this.nextPage = nextPage;
        isLoading.setValue(false);

        /*datos en liveData NO hay datos*/
        if (characters.getValue() == null) {
            characters.setValue(newCharacters);
        } else {
            /*hay datos*/
            currentCharacters = characters.getValue();
            currentCharacters.addAll(newCharacters);
            characters.setValue(currentCharacters);
        }

    }

}
