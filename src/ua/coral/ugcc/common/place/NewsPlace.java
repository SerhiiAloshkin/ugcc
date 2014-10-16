package ua.coral.ugcc.common.place;

import com.google.gwt.place.shared.PlaceTokenizer;

public class NewsPlace extends AbstractPlace {

    public NewsPlace(final String token) {
        super(token);
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
