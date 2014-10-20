package ua.coral.ugcc.common.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import ua.coral.ugcc.common.activity.AppActivityMapper;
import ua.coral.ugcc.common.place.AppPlaceHistoryMapper;
import ua.coral.ugcc.common.view.ClientFactory;

public abstract class AbstractEntryPoint implements EntryPoint {

    private final SimplePanel simplePanel = new SimplePanel();

    @Override
    public void onModuleLoad() {
        final ClientFactory clientFactory = GWT.create(ClientFactory.class);
        final EventBus eventBus = clientFactory.getEventBus();
        final PlaceController placeController = clientFactory.getPlaceController();

        final ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        final ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(simplePanel);

        final AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, getPlace());

        RootPanel.get().add(simplePanel);
        historyHandler.handleCurrentHistory();
    }

    protected abstract Place getPlace();
}
