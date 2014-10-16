package ua.coral.ugcc.user.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewsPlace extends Place {
    private String token;

    public NewsPlace(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<NewsPlace> {
        @Override
        public NewsPlace getPlace(final String token) {
            return new NewsPlace(token);
        }

        @Override
        public String getToken(final NewsPlace place) {
            return place.getToken();
        }
    }
}
