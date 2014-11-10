package ua.coral.ugcc.admin.client.activity;

import ua.coral.ugcc.admin.client.AdminModeServiceDelegate;
import ua.coral.ugcc.admin.client.view.ClientFactory;
import ua.coral.ugcc.admin.client.view.ListNewsView;
import ua.coral.ugcc.common.dto.impl.News;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ListNewsActivity extends AbstractActivity implements ListNewsView.Presenter {

    private ClientFactory clientFactory;
    private AdminModeServiceDelegate serviceDelegate;

    public ListNewsActivity(final ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        serviceDelegate = new AdminModeServiceDelegate();
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        final ListNewsView view = clientFactory.getListNewsView();
        view.setPresenter(this);
        serviceDelegate.setView(view);
        view.init();
        panel.setWidget(view.asWidget());
    }

    @Override
    public void goTo(final Place place) {
        clientFactory.getPlaceController().goTo(place);
    }

    @Override
    public void listNews() {
        serviceDelegate.listNews();
    }

    @Override
    public void addNews(final News news) {
        serviceDelegate.addNews(news);
    }

    @Override
    public void removeNews(final News news) {
        serviceDelegate.removeNews(news);
    }

    @Override
    public void updateNews(final News news) {
        serviceDelegate.updateNews(news);
    }
}
