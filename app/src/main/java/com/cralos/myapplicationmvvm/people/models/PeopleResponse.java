package com.cralos.myapplicationmvvm.people.models;

import java.util.List;

public class PeopleResponse {

    private int count;
    private String next;
    private String previous;
    private List<Character> results = null;

    /**
     * @param next
     * @param previous
     * @param count
     * @param characters
     */
    public PeopleResponse(int count, String next, String previous, List<Character> characters) {
        super();
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = characters;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Character> getCharacters() {
        return results;
    }

    public void setCharacters(List<Character> characters) {
        this.results = characters;
    }

}
