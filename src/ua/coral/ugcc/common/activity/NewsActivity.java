package ua.coral.ugcc.common.activity;

import ua.coral.ugcc.common.place.NewsPlace;
import ua.coral.ugcc.common.view.ClientFactory;
import ua.coral.ugcc.common.view.View;

public class NewsActivity extends AbstractUGCCActivity {

    public NewsActivity(final NewsPlace place, final ClientFactory clientFactory) {
        super(place.getToken(), clientFactory);
    }

    @Override
    protected View getView(final ClientFactory clientFactory) {
        return clientFactory.getNewsView();
    }
}
