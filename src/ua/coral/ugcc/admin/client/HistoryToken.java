package ua.coral.ugcc.admin.client;

public enum HistoryToken {

    LIST_NEWS("list_news"),
    ADD_NEWS("add_news"),
    UPDATE_NEWS("update_news");

    private String token;

    HistoryToken(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
