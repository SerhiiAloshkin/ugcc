package ua.coral.ugcc.user.client.view;

import com.google.gwt.place.shared.Place;

public interface View {

    void setPresenter(Presenter presenter);

    public interface Presenter {
        void goTo(Place place);
    }
}
