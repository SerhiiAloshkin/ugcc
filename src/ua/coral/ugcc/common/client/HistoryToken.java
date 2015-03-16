package ua.coral.ugcc.common.client;

public enum HistoryToken {

    ADD_NEWS("add_news"),
    UPDATE_NEWS("update_news"),

    TO_MAIN("main"),
    TO_NEWS("news"),
    TO_PARISH("parish"),
    TO_SCHEDULE("schedule"),
    TO_CONTACTS("contacts"),
    TO_MAP("map"),
    TO_DIRECTION("direction");

    private String token;

    HistoryToken(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}