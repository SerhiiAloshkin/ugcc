package ua.coral.ugcc.common.activity;

import ua.coral.ugcc.common.place.MainPlace;
import ua.coral.ugcc.common.view.ClientFactory;
import ua.coral.ugcc.common.view.MainView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MainActivity extends AbstractActivity implements MainView.Presenter {

    private ClientFactory clientFactory;
    private String name;

    public MainActivity(final MainPlace place, final ClientFactory clientFactory) {
        this.name = place.getToken();
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        final MainView mainView = clientFactory.getMainView();
        mainView.setContent(name);
        mainView.setPresenter(this);
        panel.setWidget(mainView.asWidget());
    }

    @Override
    public void goTo(final Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}
