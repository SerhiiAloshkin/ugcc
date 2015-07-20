package ua.coral.ugcc.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class Ugcc implements EntryPoint {

    @Override
    public void onModuleLoad() {
        final UgccServiceAsync rpcService = GWT.create(UgccService.class);
        final HandlerManager eventBus = new HandlerManager(null);
        final UgccController appViewer = new UgccController(rpcService, eventBus);
        appViewer.go(RootPanel.get());
    }
}
