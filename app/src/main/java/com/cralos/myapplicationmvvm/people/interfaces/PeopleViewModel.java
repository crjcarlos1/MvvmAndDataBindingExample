package com.cralos.myapplicationmvvm.people.interfaces;

import com.cralos.myapplicationmvvm.people.models.Character;

import java.util.List;

public interface PeopleViewModel {
    void setMessage(String message);

    void setCharacters(List<Character> characters,String nextPage);
}
