package ua.coral.ugcc.user.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.user.client.event.ListNewsEvent;

public interface ListNewsEventHandler extends EventHandler {

    void onListNews(ListNewsEvent event);
}
