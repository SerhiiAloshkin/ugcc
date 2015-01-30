package ua.coral.ugcc.admin.client.activity;

import ua.coral.ugcc.admin.client.view.ClientFactory;
import ua.coral.ugcc.common.place.AbstractPlace;
import ua.coral.ugcc.common.place.ListNewsPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

    private ClientFactory clientFactory;

    public AppActivityMapper(final ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(final Place place) {
        final AbstractPlace nativePlace = (AbstractPlace) place;
        if (place instanceof ListNewsPlace) {
            return new ListNewsActivity(clientFactory, nativePlace.getAttributes());
        }
        return null;
    }
}
