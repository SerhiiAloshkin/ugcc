package ua.coral.ugcc.user.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MainPlace extends Place {
    private String token;

    public MainPlace(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<MainPlace> {
        @Override
        public MainPlace getPlace(final String token) {
            return new MainPlace(token);
        }

        @Override
        public String getToken(final MainPlace place) {
            return place.getToken();
        }
    }
}
