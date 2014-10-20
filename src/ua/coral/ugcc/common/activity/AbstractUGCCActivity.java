package ua.coral.ugcc.common.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ua.coral.ugcc.common.view.ClientFactory;
import ua.coral.ugcc.common.view.View;

public abstract class AbstractUGCCActivity extends AbstractActivity implements View.Presenter {

    private ClientFactory clientFactory;
    private String name;

    public AbstractUGCCActivity(final String name, final ClientFactory clientFactory) {
        this.name = name;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        final View newsView = getView(clientFactory);
        newsView.setContent(name);
        newsView.setPresenter(this);
        panel.setWidget(newsView.asWidget());
    }

    protected abstract View getView(final ClientFactory clientFactory);

    @Override
    public void goTo(final Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}
