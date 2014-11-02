package ua.coral.ugcc.admin.client.activity;

import ua.coral.ugcc.admin.client.view.ClientFactory;
import ua.coral.ugcc.common.place.MainPlace;

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
        if (place instanceof MainPlace) {
            return new MainActivity(clientFactory);
        }
        return null;
    }
}
