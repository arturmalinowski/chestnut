package net.wazim.chestnut.domain;

public class Item {

    private String imdbId;
    private String title;
    private String type;
    private String plot;
    private String poster;

    public Item(String imdbId, String title, String poster, String type, String plot) {
        this.imdbId = imdbId;
        this.title = title;
        this.poster = poster;
        this.type = type;
        this.plot = plot;
    }

    public Item() {}

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    public String getPlot() {
        return plot;
    }
}
