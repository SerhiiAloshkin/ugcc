package ua.coral.ugcc.common.place;

import com.google.gwt.place.shared.PlaceTokenizer;

public class ListNewsPlace extends AbstractPlace {

    public ListNewsPlace(final String token) {
        super(token);
    }

    public static class Tokenizer implements PlaceTokenizer<ListNewsPlace> {
        @Override
        public ListNewsPlace getPlace(final String token) {
            return new ListNewsPlace(token);
        }

        @Override
        public String getToken(final ListNewsPlace place) {
            return place.getToken();
        }
    }
}
