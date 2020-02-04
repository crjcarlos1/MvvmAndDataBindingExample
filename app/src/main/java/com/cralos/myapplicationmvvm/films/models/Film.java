package com.cralos.myapplicationmvvm.films.models;

public class Film {

    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    private String created;
    private String edited;
    private String url;

    /**
     * @param releaseDate
     * @param edited
     * @param director
     * @param created
     * @param openingCrawl
     * @param producer
     * @param title
     * @param episodeId
     * @param url
     */
    public Film(String title, int episodeId, String openingCrawl, String director, String producer, String releaseDate, String created, String edited, String url) {
        super();
        this.title = title;
        this.episode_id = episodeId;
        this.opening_crawl = openingCrawl;
        this.director = director;
        this.producer = producer;
        this.release_date = releaseDate;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeId() {
        return episode_id;
    }

    public void setEpisodeId(int episodeId) {
        this.episode_id = episodeId;
    }

    public String getOpeningCrawl() {
        return opening_crawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.opening_crawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
