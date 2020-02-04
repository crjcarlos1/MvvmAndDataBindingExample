package com.cralos.myapplicationmvvm.films.models;

import java.util.List;

public class FilmsResponse {

    private int count;
    private List<Film> results = null;

    /**
     * @param count
     * @param results
     */
    public FilmsResponse(int count, List<Film> results) {
        super();
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Film> getFilms() {
        return results;
    }

    public void setFilms(List<Film> results) {
        this.results = results;
    }

}
