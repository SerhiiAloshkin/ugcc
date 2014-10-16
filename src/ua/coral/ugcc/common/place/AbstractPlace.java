package ua.coral.ugcc.common.place;

import com.google.gwt.place.shared.Place;

public abstract class AbstractPlace extends Place {

    private String token;

    public AbstractPlace(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
