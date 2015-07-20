package ua.coral.ugcc.user.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import ua.coral.ugcc.user.client.event.OpenedNewsEvent;

public interface OpenedNewsEventHandler extends EventHandler {

    void onOpenedNews(OpenedNewsEvent event);
}
