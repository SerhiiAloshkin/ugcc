package ua.coral.ugcc.common.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ua.coral.ugcc.common.place.NewsPlace;
import ua.coral.ugcc.common.view.ClientFactory;
import ua.coral.ugcc.common.view.NewsView;

public class NewsActivity extends AbstractActivity implements NewsView.Presenter {

    private ClientFactory clientFactory;
    private String name;

    public NewsActivity(final NewsPlace place, final ClientFactory clientFactory) {
        this.name = place.getToken();
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        final NewsView newsView = clientFactory.getNewsView();
        newsView.setContent(name);
        newsView.setPresenter(this);
        panel.setWidget(newsView.asWidget());
    }

    @Override
    public void goTo(final Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}
