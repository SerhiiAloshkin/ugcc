package ua.coral.ugcc.common.activity;

import ua.coral.ugcc.common.place.MainPlace;
import ua.coral.ugcc.common.view.ClientFactory;
import ua.coral.ugcc.common.view.View;

public class MainActivity extends AbstractUGCCActivity {

    public MainActivity(final MainPlace place, final ClientFactory clientFactory) {
        super(place.getToken(), clientFactory);
    }

    @Override
    protected View getView(final ClientFactory clientFactory) {
        return clientFactory.getMainView();
    }
}
