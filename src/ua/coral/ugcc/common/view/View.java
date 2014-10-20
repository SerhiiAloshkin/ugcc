package ua.coral.ugcc.common.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface View extends IsWidget {

    void setPresenter(Presenter presenter);

    void setContent(String content);

    public interface Presenter {
        void goTo(Place place);
    }
}
