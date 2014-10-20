package ua.coral.ugcc.user.client;

import com.google.gwt.place.shared.Place;
import ua.coral.ugcc.common.client.AbstractEntryPoint;
import ua.coral.ugcc.common.place.MainPlace;

public class Ugcc extends AbstractEntryPoint {

    @Override
    protected Place getPlace() {
        return new MainPlace("Main");
    }
}
