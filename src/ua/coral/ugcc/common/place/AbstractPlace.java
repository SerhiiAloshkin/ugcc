package ua.coral.ugcc.common.place;

import java.util.Map;

import com.google.gwt.place.shared.Place;

public abstract class AbstractPlace extends Place {

    private String token;

    private Map<String, Object> attributes;

    public AbstractPlace(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
