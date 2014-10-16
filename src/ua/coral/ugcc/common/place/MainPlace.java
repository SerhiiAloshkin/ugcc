package ua.coral.ugcc.common.place;

import com.google.gwt.place.shared.PlaceTokenizer;

public class MainPlace extends AbstractPlace {

    public MainPlace(final String token) {
        super(token);
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
