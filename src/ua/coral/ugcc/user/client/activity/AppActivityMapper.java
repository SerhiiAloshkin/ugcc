package ua.coral.ugcc.user.client.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ua.coral.ugcc.user.client.place.MainPlace;
import ua.coral.ugcc.user.client.place.NewsPlace;
import ua.coral.ugcc.user.client.view.ClientFactory;

public class AppActivityMapper implements ActivityMapper {

    private ClientFactory clientFactory;

    public AppActivityMapper(final ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(final Place place) {
        if (place instanceof MainPlace) {
            return new MainActivity((MainPlace) place, clientFactory);
        } else if (place instanceof NewsPlace) {
            return new NewsActivity((NewsPlace) place, clientFactory);
        }
        return null;
    }
}
