package net.wazim.chestnut.domain;

public class ItemUserRequest {

    private String imdbId;
    private String userId;

    public ItemUserRequest() {}

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
