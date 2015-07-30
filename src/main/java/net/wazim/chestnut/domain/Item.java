package net.wazim.chestnut.domain;

public class Item {

    private String imdbId;
    private String title;
    private String type;
    private String poster;

    public Item(String imdbId, String title, String type, String poster) {
        this.imdbId = imdbId;
        this.title = title;
        this.type = type;
        this.poster = poster;

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
}
